package multithreading;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import main.LogEntry;

public class ConsumerReport implements Runnable {

	private int count = 0;
	private Map<String, Integer> dict;
	private LogEntry maxSize;
	private long totalSize = 0;
	private final int amountOfLines;
	private BlockingQueue<LogEntry> queueLogs;
	private Date startDate;
	private Date finishDate;
	
	public static volatile boolean runnable;
	public static ActiveHostsReport activeHosts;
	public static TotalReplySizeReport totalReplySize;
	public static MaxReplyBytesReport maxReplyBytes;

	ConsumerReport(int amountOfLines, BlockingQueue<LogEntry> queueLogs,
			Date startDate, Date finishDate) {
		dict = new HashMap<String, Integer>();
		maxSize = new LogEntry();
		this.amountOfLines = amountOfLines;
		this.queueLogs = queueLogs;

		this.startDate = startDate;
		this.finishDate = finishDate;

		runnable = true;
	}

	@Override
	public void run() {
		try {
			while (runnable && count < amountOfLines) {
				consume(queueLogs.take());
			}

			List<Map.Entry<String, Integer>> list = new ArrayList<>(
					dict.entrySet());
			Collections.sort(list,
					(a, b) -> b.getValue().compareTo(a.getValue()));
			if (ActiveHostsReport.numberOfHosts > list.size()) {
				activeHosts = new ActiveHostsReport(list);
			} else {
				activeHosts = new ActiveHostsReport(list.subList(0,
						ActiveHostsReport.numberOfHosts));
			}

			totalReplySize = new TotalReplySizeReport(totalSize);

			maxReplyBytes = new MaxReplyBytesReport(maxSize);
		} catch (InterruptedException ex) {
		}
	}

	private void consume(LogEntry log) throws InterruptedException {
		++count;
		if (!(log.getTimestamp().before(startDate) || log.getTimestamp().after(
				finishDate))) {
			int temp = log.getReplyBytes();
			if (temp > maxSize.getReplyBytes()) {
				maxSize = log;
			}
			totalSize += temp;

			Object host = log.getHost();
			if (dict.containsKey(host.toString())) {
				dict.put(host.toString(), dict.get(host.toString()) + 1);
			} else {
				dict.put(host.toString(), 1);
			}
		} else if (log.getTimestamp().after(finishDate)) {
			runnable = false;
		}
	}
}
