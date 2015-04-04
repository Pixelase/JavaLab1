public class MaxReplySizeReport {
	public LogEntry maxReplySizeEtry;

	public LogEntry getMaxReplySizeEtry() {
		return maxReplySizeEtry;
	}

	public void setMaxReplySizeEtry(LogEntry maxReplySizeEtry) {
		this.maxReplySizeEtry = maxReplySizeEtry;
	}

	@Override
	public String toString() {
		return "Max Reply Size Report\n\nRequest="
				+ maxReplySizeEtry.getRequest() + "\nReply Bytes= "
				+ maxReplySizeEtry.getReplyBytes();
	}
}
