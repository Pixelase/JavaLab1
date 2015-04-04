import java.util.List;

public class MaxRequestCountReport {
	private List<Object> hosts;

	public List<Object> getHosts() {
		return hosts;
	}

	public void setHosts(List<Object> hosts) {
		this.hosts = hosts;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Object item : hosts) {
			sb.append(item.toString() + "\n");
		}
		return "Max Request Count Report\n\n" + sb;
	}

}
