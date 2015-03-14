import java.io.FileNotFoundException;

public class Program {
	public static void main(String[] args) throws FileNotFoundException {
		
		ILogProcessor logProcessor = new LogProcessor();
		logProcessor.process(args);
		
	}
}
