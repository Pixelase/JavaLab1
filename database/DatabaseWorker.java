package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import main.LogEntry;

public class DatabaseWorker {
	public static Connection connection;
	public static Statement statement;
	public static ResultSet resultSet;
	private static Scanner scan;

	public static void connectToDatabase(String path)
			throws ClassNotFoundException, SQLException {
		connection = null;
		Class.forName("org.sqlite.JDBC");
		connection = DriverManager.getConnection("jdbc:sqlite:" + path);

	}

	public static void createDatabase() throws ClassNotFoundException,
			SQLException {
		statement = connection.createStatement();
		statement
				.execute("CREATE TABLE if not exists 'logs' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'host' text, 'date' TIMESTAMP, 'request' text, 'replyCode' INT, 'replySize' INT);");

	}

	public static void writeDatabase(LogEntry log) throws SQLException {

		Timestamp sqlTimestamp = new Timestamp(log.getTimestamp().getTime());
		statement
				.execute(String
						.format("INSERT INTO 'logs' ('host', 'date', 'request', 'replyCode', 'replySize') VALUES ('%1$s', '%2$s','%3$s', %4$d, %5$d); ",
								log.getHost().toString(), sqlTimestamp,
								log.getRequest(), log.getHTTPReplyCode(),
								log.getReplyBytes()));
	}

	public static void readDatabase(String query, String[] args)
			throws ClassNotFoundException, SQLException {

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

		Timestamp sqlTimestamp1 = new Timestamp(startDate.getTime());
		Timestamp sqlTimestamp2 = new Timestamp(endDate.getTime());
		long readFrom = Long.parseLong(args[2]);
		long rowsToRead = Long.parseLong(args[3]);

		String fullQuery = String
				.format("select %1$s from logs where id >= '%2$s' and id < '%3$s' and date >= '%4$s' and date <= '%5$s'",
						query, readFrom, readFrom + rowsToRead, sqlTimestamp1,
						sqlTimestamp2);

		resultSet = statement.executeQuery(fullQuery);
		while (resultSet.next()) {
			System.out.println(query + " " + resultSet.getInt(query));
		}
	}

	public static void closeDatabase() throws ClassNotFoundException,
			SQLException {
		connection.close();
		statement.close();
		resultSet.close();
	}

}
