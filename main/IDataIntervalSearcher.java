package main;
import java.util.Date;
import java.util.List;

public interface IDataIntervalSearcher {

	public List<LogEntry> find(List<LogEntry> data, Date startDate, Date endDate);

}