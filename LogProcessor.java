public class LogProcessor implements ILogProcessor {

	@Override
	public void process(String[] args) {
		ILogParser logParser = new LogParser();
		ILogReader logReader = new LogReader();
		ILogWriter logWriter = new LogWriter();

		try {
			String sourcePath = args[0];
			String destinationPath = args[1];
			long readFrom = Long.parseLong(args[2]);
			long rowsToRead = Long.parseLong(args[3]);
			logWriter.write(destinationPath, logParser.parse(logReader.read(
					sourcePath, readFrom, rowsToRead)));
		}

		catch (Exception e) {
			System.out.println("Что-то пошло не так:\n"
					+ e.getMessage().toString());
		}
	}
}
