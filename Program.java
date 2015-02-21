import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;



public class Program
{	
    public static void main(String[] args) throws FileNotFoundException
    {
    	try{
    		String fileName = args[0];
    		int ReadFrom = Integer.parseInt(args[1]);
    		int RowsToRead = Integer.parseInt(args[2]);    	
        	System.out.println(Program.read(fileName, ReadFrom, RowsToRead));
    	}
    	
    	catch(Exception e)
    	{
    		System.out.println("Что-то пошло не так");
    	}
    }
    
    private static void exists(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists()){
            throw new FileNotFoundException(file.getName());
        }
    }
    
	public static String read(String fileName, int readFrom, int rowsToRead ) throws FileNotFoundException {
	    //Этот спец. объект для построения строки
	    StringBuilder sb = new StringBuilder();
	 
	    exists(fileName);
	 
	    try {
	        //Объект для чтения файла в буфер
	        BufferedReader in = new BufferedReader(new FileReader(fileName));
	        try {
	            //В цикле построчно считываем файл
	        	String s;
	        	int count = 0;
	            while ((s = in.readLine()) != null && count - readFrom + 1 != rowsToRead ) {
	             	count++;
	            	if(count >= readFrom - 1)
	            	{
	                sb.append(s);
	                sb.append("\n");
	            	}
	            }          
	            
	        }	        
	    	catch(Exception ex)
	    	{
	    		System.out.println(ex.getMessage());
	    	}
	        
	        finally {
	            //Также не забываем закрыть файл
	            in.close();
	        }
	    } catch(IOException e) {	    	
	        throw new RuntimeException(e);
	    }
	 
	    //Возвращаем полученный текст с файла
	    if (sb.length() == 0) return "Неверные введённые параметры или файл пустой\n";
	    return sb.toString();
	}
}
