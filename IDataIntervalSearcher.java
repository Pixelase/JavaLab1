import java.util.Date;
import java.util.List;

public interface IDataIntervalSearcher {

	public List<LogEntry> Find(List<LogEntry> data, Date begin, Date end);

}