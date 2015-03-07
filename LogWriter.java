import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class LogWriter {

	public static void write(String fileName, String data)
			throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(fileName);
		writer.print(data);
		writer.close();
	}

}
