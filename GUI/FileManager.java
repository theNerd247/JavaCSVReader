import java.util.ArrayList;

//class to manage file operations for the GUI interface
public class FileManager
{
	public static ArrayList<CSVFile> fileList;
	public static CSVFile openFile(String path)
	{
		CSVFile newFile = new CSVFile(path);
		fileList.add(newFile);
		return newFile;
	}
}	
