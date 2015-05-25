package multithreading;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

	private LineNumberReader reader;
	private int startLineNumber;
	private int amountOfLines;
	private BlockingQueue<String> queueLines;

	Producer(String path, int startLineNumber , int amountOfLines, BlockingQueue<String> queueLines) {
		try {
			reader = new LineNumberReader(new FileReader(path));
			this.startLineNumber = startLineNumber;
			this.amountOfLines = amountOfLines;
			this.queueLines = queueLines;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		try {
			String line;
			while (amountOfLines >= 0 && (line = produce()) != null) {
				queueLines.put(line);

			}
			reader.close();
		}
		 catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	private String produce() {
		try {
			String line;
			do {
				line = reader.readLine();
			} while (reader.getLineNumber() < startLineNumber);
			--amountOfLines;
			return line;
		} catch (IOException e) {
			return null;
		}
	}
}
