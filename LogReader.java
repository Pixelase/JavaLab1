import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogReader implements ILogReader {

	@Override
	public List<String> read(String fileName, long readFrom, long rowsToRead)
			throws FileNotFoundException {

		List<String> logEntries = new ArrayList<String>();

		try {
			// Объект для чтения файла в буфер
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			try {
				// В цикле построчно считываем файл
				String s;
				int count = 1;
				while ((s = in.readLine()) != null
						&& count - readFrom != rowsToRead) {

					if (count >= readFrom) {

						logEntries.add(s);
					}
					count++;
				}
			}

			finally {
				in.close();
			}
		}

		catch (IOException e) {
			throw new RuntimeException(e);
		}

		// Возвращаем полученный текст с файла
		return logEntries;
	}

}
