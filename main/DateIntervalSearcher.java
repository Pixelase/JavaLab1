package main;
import java.util.Date;
import java.util.List;

public class DateIntervalSearcher implements IDataIntervalSearcher {
	@Override
	public List<LogEntry> find(List<LogEntry> data, Date startDate, Date endDate) {
		int resultedBeginIndex;
		int resultedEndIndex;
		resultedBeginIndex = findFirst(data, startDate);
		resultedEndIndex = findFirst(data, endDate);
		return data.subList(resultedBeginIndex, resultedEndIndex);
	}

	private int findFirst(List<LogEntry> data, Date date) {
		int index = 0;
		for (LogEntry item : data) {
			if (item.getTimestamp().toString().contentEquals(date.toString())) {
				return index + 1;
			}
			index++;
		}
		return index;
	}
}
