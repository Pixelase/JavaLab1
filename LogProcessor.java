import java.io.FileNotFoundException;


public class LogProcessor implements ILogProcessor {
	
	@Override
	public void process(String[] args) throws FileNotFoundException {
		ILogParser logParser = new LogParser();
		ILogReader logReader = new LogReader();
		ILogWriter logWriter = new LogWriter();
		try {
			String inFileName = args[0];
			String outFileName = args[1];
			long readFrom = Long.parseLong(args[2]);
			long rowsToRead = Long.parseLong(args[3]);
			logWriter.write(outFileName, logParser.parse(logReader.read(
					inFileName, readFrom, rowsToRead)));
		}

		catch (FileNotFoundException e) {
			System.out.println("Файл не найден\n");
		}

		catch (Exception e) {
			System.out.println("Что-то пошло не так:\n"
					+ e.getMessage().toString());
		}
	}
}
