package multithreading;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

	private DataInputStream reader;
	private int readFrom;
	private int rowsToRead;
	private BlockingQueue<String> linesQueue;

	Producer(String path, int readFrom, int rowsToRead,
			BlockingQueue<String> linesQueue) {
		try {
			reader = new DataInputStream(new FileInputStream(path));
			this.readFrom = readFrom;
			this.rowsToRead = rowsToRead;
			this.linesQueue = linesQueue;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		try {
			String line;
			while (rowsToRead >= 0 && (line = produce()) != null) {
				linesQueue.put(line);
			}
			reader.close();
		} catch (IOException | InterruptedException e) {
			System.out.println("\nЧто-то пошло не так: \n" + e.getMessage()
					+ "\n");
		}
	}

	private String produce() {
		try {
			String line;
			int lineNumber = 0;
			do {
				line = reader.readUTF();
				lineNumber++;
			} while (lineNumber < readFrom);
			--rowsToRead;
			return line;
		} catch (IOException e) {
			return null;
		}
	}
}
