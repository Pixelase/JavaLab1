package multithreading;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import main.LogEntry;
import main.MaxReplySizeReport;
import main.MaxRequestCountReport;
import main.TotalRepliesSizeReport;

public class ConsumerReport implements Runnable {

	private int count = 0;
	private Map<String, Integer> dict;
	private LogEntry maxSizeEntry;
	private int totalSize = 0;
	private final int rowsToRead;
	private BlockingQueue<LogEntry> entriesQueue;
	private Date startDate;
	private Date endDate;

	public static volatile boolean runnable;
	public static MaxRequestCountReport maxRequestCountReport;
	public static TotalRepliesSizeReport totalRepliesSizeReport;
	public static MaxReplySizeReport maxReplySizeReport;

	ConsumerReport(int rowsToRead, BlockingQueue<LogEntry> entriesQueue,
			Date startDate, Date endDate) {
		dict = new HashMap<String, Integer>();
		maxSizeEntry = new LogEntry();
		this.rowsToRead = rowsToRead;
		this.entriesQueue = entriesQueue;

		this.startDate = startDate;
		this.endDate = endDate;

		runnable = true;
	}

	@Override
	public void run() {
		try {
			while (runnable && count < rowsToRead) {
				consume(entriesQueue.take());
			}

			List<Map.Entry<String, Integer>> list = new ArrayList<>(
					dict.entrySet());
			Collections.sort(list,
					(a, b) -> b.getValue().compareTo(a.getValue()));
			maxRequestCountReport = new MaxRequestCountReport();
			if (MaxRequestCountReport.numberOfHosts > list.size()) {
				maxRequestCountReport.setHosts(list);
				;
			} else {
				maxRequestCountReport.setHosts(list.subList(0,
						MaxRequestCountReport.numberOfHosts));
			}

			totalRepliesSizeReport = new TotalRepliesSizeReport();
			totalRepliesSizeReport.totalSize = totalSize;

			maxReplySizeReport = new MaxReplySizeReport();
			maxReplySizeReport.setMaxReplySizeEtry(maxSizeEntry);
		} catch (InterruptedException ex) {
		}
	}

	private void consume(LogEntry log) throws InterruptedException {
		++count;
		if (!(log.getTimestamp().before(startDate) || log.getTimestamp().after(
				endDate))) {
			int temp = log.getReplyBytes();
			if (temp > maxSizeEntry.getReplyBytes()) {
				maxSizeEntry = log;
			}
			totalSize += temp;

			Object host = log.getHost();
			if (dict.containsKey(host.toString())) {
				dict.put(host.toString(), dict.get(host.toString()) + 1);
			} else {
				dict.put(host.toString(), 1);
			}
		} else if (log.getTimestamp().after(endDate)) {
			runnable = false;
		}
	}
}
