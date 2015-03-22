import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class LogParser implements ILogParser {

	@Override
	public LogEntry parse(String line) throws UnknownHostException {

		LogEntry logEntry = new LogEntry();

		char quotationMark = '\"';
		int indexOfQuotationMark = line.indexOf(quotationMark);
		int lastIndexOfQuotationMark = line.lastIndexOf(quotationMark);
		int lastIndexOfSpace = line.lastIndexOf(" ");
		final String pattern = "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
		if (Pattern.compile(pattern)
				.matcher(line.substring(0, line.indexOf(" - - "))).matches()) {
			logEntry.setHost(InetAddress.getByName(line.substring(0,
					line.indexOf(" - - "))));
		} else {
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

	@Override
	public List<LogEntry> parse(List<String> data) throws UnknownHostException {

		List<LogEntry> logEntries = new ArrayList<LogEntry>();

		for (String str : data) {
			logEntries.add(parse(str));
		}

		return logEntries;
	}
}
