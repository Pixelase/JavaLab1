import java.io.FileNotFoundException;

public class Program {
	public static void main(String[] args) throws FileNotFoundException {
		try {
			String inFileName = args[0];
			String outFileName = args[1];
			long readFrom = Long.parseLong(args[2]);
			long rowsToRead = Long.parseLong(args[3]);
			LogWriter.write(outFileName, LogParser.parse(LogReader.read(
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
