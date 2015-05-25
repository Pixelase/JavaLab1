package multithreading;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;

import main.ILogParser;
import main.LogEntry;
import main.LogParser;

class Consumer implements Runnable {

	private ILogParser parser;
	private static volatile PrintWriter out;
	private static volatile int count = 0;
	private final int amountOfLines;
	private BlockingQueue<String> queueLines;
	private BlockingQueue<LogEntry> queueLogs;

	Consumer(String path, int amountOfLines, BlockingQueue<String> queueLines,
			BlockingQueue<LogEntry> queueLogs) throws IOException {
		parser = new LogParser();
		this.amountOfLines = amountOfLines;
		this.queueLines = queueLines;
		this.queueLogs = queueLogs;
		out = new PrintWriter(new BufferedWriter(new FileWriter(path)));
	}

	@Override
	public void run() {

		try {
			while (count < amountOfLines) {

				consume(queueLines.take());
			}
			out.close();
		} catch (InterruptedException | SQLException ex) {
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void consume(String line) throws InterruptedException, SQLException, UnknownHostException {
		++count;
		main.LogEntry entry = parser.parse(line);
		out.println(entry);
		//DatabaseConnect.WriteDatabase(log, count);
		if (ConsumerReport.runnable) {
			queueLogs.put(entry);
		}
	}
}