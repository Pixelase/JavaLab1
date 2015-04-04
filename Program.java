import java.io.FileNotFoundException;
import java.text.ParseException;

public class Program {
	public static void main(String[] args) throws FileNotFoundException,
			ParseException {

		ILogProcessor logProcessor = new LogProcessor();
		IArgsChecker argsChecker = new ArgsChecker(args);

		if (argsChecker.isArgsCorrect()) {
			// logProcessor.process(args);
			logProcessor.createReport(args);
		} else {
			argsChecker.ShowCheckingInfo();
		}

	}
}
