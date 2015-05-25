package main;
import java.io.File;
import java.text.ParseException;

public class ArgsChecker implements IArgsChecker {

	private String[] args;
	private String sourcePath;
	private String destinationPath;
	private long readFrom;
	private long rowsToRead;
	private int reportNumber;

	public ArgsChecker(String[] Args) {
		args = Args;
	}

	@Override
	public boolean isArgsCorrect() throws ParseException {
		if (args.length == 5) {
			sourcePath = args[0];
			destinationPath = args[1];
			readFrom = Long.parseLong(args[2]);
			rowsToRead = Long.parseLong(args[3]);
			reportNumber = Integer.parseInt(args[4]);
			if (isSourceCorrect(sourcePath)
					&& isDestinationCorrect(destinationPath)
					&& isTheLowerBoundCorrect(readFrom)
					&& isAmountCorrect(rowsToRead)
					&& isReportNumberCorrect(reportNumber)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void ShowCheckingInfo() throws ParseException {
		if (isArgsCorrect()) {
			System.out.println("Ввёденные параметры корректны\n");
		} else {
			System.out.println("Введены некорректные параметры:\n");
			if (!isSourceCorrect(sourcePath)) {
				System.out.println("* Проблемы с исходным файлом\n");
			}

			if (!isDestinationCorrect(destinationPath)) {
				System.out.println("* Проблемы с файлом назначения\n");
			}

			if (!isTheLowerBoundCorrect(readFrom)) {
				System.out.println("* Некорректная нижняя граница\n");
			}

			if (!isAmountCorrect(rowsToRead)) {
				System.out.println("* Неверное количество считываемых строк\n");
			}
		}
	}

	private boolean isSourceCorrect(String path) {
		File file = new File(path);
		if (file.exists() && file.canRead()) {
			return true;
		}

		return false;
	}

	private boolean isDestinationCorrect(String path) {
		File file = new File(path);
		if (!file.exists() || file.canWrite()) {
			return true;
		}
		return false;
	}

	private boolean isTheLowerBoundCorrect(long lowerBound) {
		if (lowerBound > 0) {
			return true;
		}
		return false;
	}

	private boolean isAmountCorrect(long amount) {
		if (amount > 0) {
			return true;
		}
		return false;
	}

	private boolean isReportNumberCorrect(int reportNumber) {
		if (reportNumber > 0) {
			return true;
		}
		return false;
	}
}
