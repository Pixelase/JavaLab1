import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.ArrayList;
import java.util.List;

public class LogParser {

	public static LogEntry parse(String line) throws UnknownHostException {

		LogEntry logEntry = new LogEntry();

		char quotationMark = '\"';
		int indexOfQuotationMark = line.indexOf(quotationMark);
		int lastIndexOfQuotationMark = line.lastIndexOf(quotationMark);
		int lastIndexOfSpace = line.lastIndexOf(" ");
		try {
			logEntry.setHost(InetAddress.getByName(line.substring(0,
					line.indexOf(" - - "))));
		} catch (UnknownHostException ex) {
			logEntry.setHost((line.substring(0, line.indexOf(" - - "))));
		}
		logEntry.setRequest(line.substring(indexOfQuotationMark,
				lastIndexOfQuotationMark) + quotationMark);
		logEntry.setHTTPReplyCode(Integer.parseInt(line.substring(
				lastIndexOfQuotationMark + 2, lastIndexOfSpace)));

		try {
			logEntry.setReplyBytes(Integer.parseInt(line
					.substring(lastIndexOfSpace + 1)));
		} catch (NumberFormatException e) {
			logEntry.setReplyBytes(0);
		}

		String timestampPattern = "[dd/MMM/yyyy:HH:mm:ss Z]";
		logEntry.setTimestamp(new SimpleDateFormat(timestampPattern, Locale.US)
				.parse(line, new ParsePosition(line.indexOf("["))));

		return logEntry;
	}

	public static List<LogEntry> parse(List<String> data)
			throws UnknownHostException {

		List<LogEntry> logEntries = new ArrayList<LogEntry>();

		for (String str : data) {
			logEntries.add(parse(str));
		}

		return logEntries;
	}

}
