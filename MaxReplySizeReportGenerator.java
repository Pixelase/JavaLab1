public class MaxReplySizeReportGenerator implements
		IReportGenerator<MaxReplySizeReport, ReportArgs> {

	@Override
	public MaxReplySizeReport generateReport(ReportArgs parameters) {
		MaxReplySizeReport report = new MaxReplySizeReport();
		LogEntry result = parameters.getData().get(0);
		for (LogEntry item : parameters.getData()) {
			if (item.getReplyBytes() > result.getReplyBytes()) {
				result = item;
			}
		}
		report.setMaxReplySizeEtry(result);
		return report;
	}
}
