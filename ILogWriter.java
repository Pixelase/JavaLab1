import java.io.FileNotFoundException;
import java.util.List;


public interface ILogWriter {
	public void write(String fileName, List<LogEntry> logEntries)
			throws FileNotFoundException;
}
