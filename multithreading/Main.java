package multithreading;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import main.ArgsChecker;
import main.IArgsChecker;
import main.LogEntry;

public class Main {

	private static Scanner scan;

	public static void main(String[] args) throws InterruptedException,
			IOException, ClassNotFoundException, SQLException, ParseException {
		IArgsChecker argsChecker = new ArgsChecker(args);
		
		if (!argsChecker.isArgsCorrect()) {
			argsChecker.ShowCheckingInfo();
			return;
		}
		
		long before = System.currentTimeMillis();
		runThreads(args);
		

		long after = System.currentTimeMillis();
		long time = (after - before);
		System.out.println("time " + time);

	}

	public static void runThreads(String[] args) throws IOException, InterruptedException, ParseException {

		final BlockingQueue<String> queueLines = new ArrayBlockingQueue<>(10000);

		final BlockingQueue<LogEntry> queueLogs = new ArrayBlockingQueue<>(
				10000);

		Thread threadProducer = new Thread(
				new Producer(args[0], 1,
						Integer.parseInt(args[3]), queueLines));
		threadProducer.setName("Producer");
		Thread threadConsumer1 = new Thread(new Consumer(
				args[1], Integer.parseInt(args[3]), queueLines,
				queueLogs));
		threadConsumer1.setName("Consumer 1");
		scan = new Scanner(System.in);
		String lineStartDate = scan.nextLine();
		String lineEndDate = scan.nextLine();
		String timestampPattern = "[dd/MMM/yyyy:HH:mm:ss Z]";
		Date startDate = new SimpleDateFormat(timestampPattern, Locale.US)
		.parse(lineStartDate);
		Date endDate = new SimpleDateFormat(timestampPattern, Locale.US)
		.parse(lineEndDate);
		Thread threadConsumerReports = new Thread(new ConsumerReport( Integer.parseInt(args[3]), queueLogs,startDate, endDate));
		threadConsumerReports.setName("Consumer Reports");

		threadProducer.start();
		threadConsumer1.start();
		threadConsumerReports.start();

		threadProducer.join();
		threadConsumer1.join();
		threadConsumerReports.join();

		System.out.println(ConsumerReport.totalReplySize);
		System.out.println(ConsumerReport.maxReplyBytes);
		System.out.println(ConsumerReport.activeHosts);
	}
}
