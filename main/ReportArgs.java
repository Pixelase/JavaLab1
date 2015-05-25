package main;
import java.util.List;

public class ReportArgs {
	public List<LogEntry> getData() {
		return data;
	}

	public void setData(List<LogEntry> data) {
		this.data = data;
	}

	public int getReportNumber() {
		return reportNumber;
	}

	public void setReportNumber(int reportNumber) {
		this.reportNumber = reportNumber;
	}

	private List<LogEntry> data;
	private int reportNumber;
}
