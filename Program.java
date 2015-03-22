import java.io.FileNotFoundException;

public class Program {
	public static void main(String[] args) throws FileNotFoundException {

		ILogProcessor logProcessor = new LogProcessor();
		IArgsChecker argsChecker = new ArgsChecker(args);

		if (argsChecker.isArgsCorrect()) {
			logProcessor.process(args);
		} else {
			argsChecker.ShowCheckingInfo();
		}

	}
}
