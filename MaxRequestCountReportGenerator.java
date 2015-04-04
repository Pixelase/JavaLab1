import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaxRequestCountReportGenerator implements
		IReportGenerator<MaxRequestCountReport, ReportArgs> {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public MaxRequestCountReport generateReport(ReportArgs parameters) {
		MaxRequestCountReport report = new MaxRequestCountReport();

		Map<String, Integer> hostMap = new HashMap<String, Integer>();
		for (LogEntry item : parameters.getData()) {
			if (!hostMap.containsKey(item.getHost().toString())) {
				hostMap.put(item.getHost().toString(), 0);
			}
			hostMap.put(item.getHost().toString(),
					hostMap.get(item.getHost().toString()) + 1);
		}
		List result = new ArrayList(hostMap.entrySet());
		Collections.sort(result, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> b,
					Map.Entry<String, Integer> a) {
				return a.getValue().compareTo(b.getValue());
			}
		});

		report.setHosts(result.subList(0, 5));
		return report;
	}
}
