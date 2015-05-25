package main;
import java.io.FileNotFoundException;
import java.util.List;


public interface ILogReader {
	
	public List<String> read(String fileName, long readFrom,
			long rowsToRead) throws FileNotFoundException;

}
