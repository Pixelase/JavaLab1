import java.text.ParseException;
import java.util.List;

public interface IReportArgsParser {
	public ReportArgs parse(List<LogEntry> data) throws ParseException;

}