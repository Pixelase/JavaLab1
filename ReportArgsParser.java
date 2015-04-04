import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class ReportArgsParser implements IReportArgsParser {

	private int reportNumber;
	private String lineStartDate;
	private String lineEndDate;
	private Scanner scan;

	@Override
	public ReportArgs parse(List<LogEntry> data) throws ParseException {
		String timestampPattern = "[dd/MMM/yyyy:HH:mm:ss Z]";
		Date startDate = new SimpleDateFormat(timestampPattern, Locale.US)
				.parse(lineStartDate);
		Date endDate = new SimpleDateFormat(timestampPattern, Locale.US)
				.parse(lineEndDate);
		ReportArgs reportArgs = new ReportArgs();
		IDataIntervalSearcher searcher = new DateIntervalSearcher();
		reportArgs.setData(searcher.find(data, startDate, endDate));
		reportArgs.setReportNumber(reportNumber);
		return reportArgs;
	}

	public ReportArgsParser(String[] args) {
		reportNumber = Integer.parseInt(args[4]);
		scan = new Scanner(System.in);
		lineStartDate = scan.nextLine();
		lineEndDate = scan.nextLine();
	}
}
