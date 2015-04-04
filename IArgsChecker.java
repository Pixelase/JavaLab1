import java.text.ParseException;

public interface IArgsChecker {

	public boolean isArgsCorrect() throws ParseException;

	public void ShowCheckingInfo() throws ParseException;

}
