import java.io.PrintWriter;

public class LogProcessor implements ILogProcessor {

	@Override
	public void process(String[] args) {
		ILogParser logParser = new LogParser();
		ILogReader logReader = new LogReader();
		ILogWriter logWriter = new LogWriter();

		try {
			String sourcePath = args[0];
			String destinationPath = args[1];
			long readFrom = Long.parseLong(args[2]);
			long rowsToRead = Long.parseLong(args[3]);
			logWriter.write(destinationPath, logParser.parse(logReader.read(
					sourcePath, readFrom, rowsToRead)));
		}

		catch (Exception e) {
			System.out.println("Что-то пошло не так:\n"
					+ e.getMessage().toString());
		}
	}

	@Override
	public void createReport(String[] args) {
		ILogParser logParser = new LogParser();
		ILogReader logReader = new LogReader();
		try {
			String sourcePath = args[0];
			String destinationPath = args[1];
			long readFrom = Long.parseLong(args[2]);
			long rowsToRead = Long.parseLong(args[3]);
			PrintWriter writer = new PrintWriter(destinationPath);
			IReportArgsParser reportArgsParser = new ReportArgsParser(args);
			ReportArgs reportArgs = reportArgsParser.parse(logParser
					.parse(logReader.read(sourcePath, readFrom, rowsToRead)));

			switch (reportArgs.getReportNumber()) {
			case 1: {
				IReportGenerator<MaxRequestCountReport, ReportArgs> mrcReportGenerator = new MaxRequestCountReportGenerator();
				writer.write(mrcReportGenerator.generateReport(reportArgs)
						.toString());
				writer.close();
				break;
			}
			case 2: {
				IReportGenerator<TotalRepliesSizeReport, ReportArgs> trsReportGenerator = new TotalRepliesSizeReportGenerator();
				writer.write(trsReportGenerator.generateReport(reportArgs)
						.toString());
				writer.close();
				break;
			}
			case 3: {
				IReportGenerator<MaxReplySizeReport, ReportArgs> mrsReportGenerator = new MaxReplySizeReportGenerator();
				writer.write(mrsReportGenerator.generateReport(reportArgs)
						.toString());
				writer.close();
				break;
			}
			}
		}

		catch (Exception e) {
			System.out.println("Что-то пошло не так:\n"
					+ e.getMessage().toString());
		}
	}
}
