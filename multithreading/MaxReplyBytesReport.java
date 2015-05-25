package multithreading;

import main.LogEntry;

public class MaxReplyBytesReport{

	private LogEntry maxReplyBytes;

	public MaxReplyBytesReport(LogEntry maxReplyBytes) {
		super();
		this.maxReplyBytes = maxReplyBytes;
	}

	@Override
	public String toString() {
		return "maxReplyBytes = " + maxReplyBytes.getReplyBytes();
	}
}
