package main;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class LogBinaryReader implements ILogReader{

	@Override
	public List<String> read(String fileName, long readFrom, long rowsToRead)
			throws FileNotFoundException {
		List<String> logEntries = new ArrayList<String>();
		
		try {
			// Объект для чтения файла в буфер
			DataInputStream reader = new DataInputStream(new FileInputStream(fileName));
			try {
				// В цикле построчно считываем файл
				String s;
				int count = 1;
				while ((s = reader.readUTF()) != null
						&& count - readFrom != rowsToRead) {

					if (count >= readFrom) {

						logEntries.add(s);
					}
					count++;
				}
			}

			finally {
				reader.close();
			}
		}

		catch (IOException e) {
			throw new RuntimeException(e);
		}

		// Возвращаем полученный текст с файла
		return logEntries;
	}

}
