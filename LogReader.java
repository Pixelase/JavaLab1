import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogReader {

	private static void exists(String fileName) throws FileNotFoundException {
		File file = new File(fileName);
		if (!file.exists()) {
			throw new FileNotFoundException(file.getName());
		}
	}

	public static List<String> read(String fileName, long readFrom,
			long rowsToRead) throws FileNotFoundException {

		List<String> logEntries = new ArrayList<String>();

		exists(fileName);

		try {

			if (readFrom <= 0 || rowsToRead < 0) {
				throw new IllegalArgumentException("Введены неверные параметры");
			}

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
