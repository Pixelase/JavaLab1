import java.io.File;

public class ArgsChecker implements IArgsChecker {

	private String[] args;
	String sourcePath;
	String destinationPath;
	long readFrom;
	long rowsToRead;

	public ArgsChecker(String[] Args) {
		args = Args;
	}

	@Override
	public boolean isArgsCorrect() {
		if (args.length == 4) {
			sourcePath = args[0];
			destinationPath = args[1];
			readFrom = Long.parseLong(args[2]);
			rowsToRead = Long.parseLong(args[3]);
			if (isSourceCorrect(sourcePath)
					&& isDestinationCorrect(destinationPath)
					&& isTheLowerBoundCorrect(readFrom)
					&& isAmountCorrect(rowsToRead)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void ShowCheckingInfo() {
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
		if (file.canWrite()) {
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
}
