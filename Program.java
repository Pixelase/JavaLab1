import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Program {
	public static void main(String[] args) throws FileNotFoundException {
		try {
			String fileName = args[0];
			long readFrom = Integer.parseInt(args[1]);
			long rowsToRead = Integer.parseInt(args[2]);
			System.out.println(Program.read(fileName, readFrom, rowsToRead));
		}

		catch (FileNotFoundException e) {
			System.out.println("Файл не найден\n");
		}

		catch (Exception e) {
			System.out.println("Что-то пошло не так:\n"
					+ e.getMessage().toString());
		}
	}

	private static void exists(String fileName) throws FileNotFoundException {
		File file = new File(fileName);
		if (!file.exists()) {
			throw new FileNotFoundException(file.getName());
		}
	}

	public static String read(String fileName, long readFrom, long rowsToRead)
			throws FileNotFoundException {

		StringBuilder sb = new StringBuilder();

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
						sb.append(s + '\n');
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
		return sb.toString();
	}
}
