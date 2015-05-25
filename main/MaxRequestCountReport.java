package main;
import java.util.List;
import java.util.Map.Entry;

public class MaxRequestCountReport {
	public static final int numberOfHosts = 5;
	private List<Entry<String, Integer>> hosts;

	public List<Entry<String, Integer>> getHosts() {
		return hosts;
	}

	public void setHosts(List<Entry<String, Integer>> hosts) {
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
