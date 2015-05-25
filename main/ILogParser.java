package main;
import java.net.UnknownHostException;
import java.util.List;

public interface ILogParser {

	public LogEntry parse(String line) throws UnknownHostException;

	public List<LogEntry> parse(List<String> data) throws UnknownHostException;

}
