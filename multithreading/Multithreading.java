package multithreading;

import java.io.IOException;
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

public class Multithreading {

	private static Scanner scan;

	public static void main(String[] args) throws InterruptedException,
			IOException, ClassNotFoundException, ParseException {
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

	public static void runThreads(String[] args) throws IOException,
			InterruptedException, ParseException {
		try {
			final BlockingQueue<String> linesQueue = new ArrayBlockingQueue<>(
					10000);

			final BlockingQueue<LogEntry> entriesQueue = new ArrayBlockingQueue<>(
					10000);

			Thread threadProducer = new Thread(new Producer(args[0], 1,
					Integer.parseInt(args[3]), linesQueue));
			threadProducer.setName("Producer");
			Thread threadConsumer1 = new Thread(new Consumer(args[1],
					Integer.parseInt(args[3]), linesQueue, entriesQueue));
			threadConsumer1.setName("Consumer 1");

			scan = new Scanner(System.in);
			String lineStartDate = scan.nextLine();
			String lineEndDate = scan.nextLine();
			String timestampPattern = "[dd/MMM/yyyy:HH:mm:ss Z]";
			Date startDate = null;
			Date endDate = null;
			try {
				startDate = new SimpleDateFormat(timestampPattern, Locale.US)
						.parse(lineStartDate);
				endDate = new SimpleDateFormat(timestampPattern, Locale.US)
						.parse(lineEndDate);
			} catch (ParseException ex) {
				System.out.println("Введён неверный формат даты");
			}
			Thread threadConsumerReports = new Thread(
					new ConsumerReport(Integer.parseInt(args[3]), entriesQueue,
							startDate, endDate));
			threadConsumerReports.setName("Consumer Reports");

			threadProducer.start();
			threadConsumer1.start();
			threadConsumerReports.start();

			threadProducer.join();
			threadConsumer1.join();
			threadConsumerReports.join();

			System.out
					.println("\n______________________________________________________________________________\n\n"
							+ ConsumerReport.totalRepliesSizeReport
							+ "\n______________________________________________________________________________\n\n\n"
							+ "\n______________________________________________________________________________\n\n"
							+ ConsumerReport.maxReplySizeReport
							+ "\n______________________________________________________________________________\n\n\n"
							+ "\n______________________________________________________________________________\n\n"
							+ ConsumerReport.maxRequestCountReport
							+ "\n______________________________________________________________________________\n");
		} catch (Exception e) {
			System.out.println("\nЧто-то пошло не так: \n" + e.getMessage());
		}
	}
}
