public class TotalRepliesSizeReportGenerator implements
		IReportGenerator<TotalRepliesSizeReport, ReportArgs> {

	@Override
	public TotalRepliesSizeReport generateReport(ReportArgs parameters) {
		TotalRepliesSizeReport report = new TotalRepliesSizeReport();
		for (LogEntry item : parameters.getData()) {
			report.totalSize += item.getReplyBytes();
		}
		return report;
	}
}
