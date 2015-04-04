import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class LogWriter implements ILogWriter {

	@Override
	public void write(String fileName, List<LogEntry> logEntries)
			throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(fileName);
		for (LogEntry item : logEntries) {
			writer.println(item.toString());
		}
		writer.close();
	}
}
