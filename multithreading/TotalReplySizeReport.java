package multithreading;

public class TotalReplySizeReport{

	private long totalReplySize;

	public TotalReplySizeReport(long totalReplySize) {
		super();
		this.totalReplySize = totalReplySize;
	}

	@Override
	public String toString() {
		return "totalReplySize = " + totalReplySize;
	}

}
