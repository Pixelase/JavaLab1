package main;
import java.io.FileNotFoundException;

public interface ILogProcessor {

	public void process(String[] args) throws FileNotFoundException;

	public void createReport(String[] args);
}
