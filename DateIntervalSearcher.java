import java.util.Date;
import java.util.List;

public class DateIntervalSearcher implements IDataIntervalSearcher {
	@Override
	public List<LogEntry> Find(List<LogEntry> data, Date begin, Date end) {
		int resultedBeginIndex;
		int resultedEndIndex;
		resultedBeginIndex = BinarySearch(data, begin);
		resultedEndIndex = BinarySearch(data, end);
		return data.subList(resultedBeginIndex, resultedEndIndex);
	}

	private int BinarySearch(List<LogEntry> data, Date date) {
		int beginIndex = 0;
		int endIndex = data.size() - 1;
		int center = 0;
		while ((beginIndex != endIndex)) {
			center = (beginIndex + endIndex) / 2;
			if (data.get(center).getTimestamp().compareTo(date) == 0) {
				return center;
			}
			if (data.get(center).getTimestamp().compareTo(date) == -1) {
				beginIndex = center + 1;
				continue;
			}
			if (data.get(center).getTimestamp().compareTo(date) == 1) {
				endIndex = center - 1;
				continue;
			}

		}
		return center - 1;
	}
}
