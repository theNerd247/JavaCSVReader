import java.util.Vector;

//class that implements program managing and control
public class GUIManager
{
	private static Vector<DataFile> dataFiles = new Vector<DataFile>();

	public static void open()
	{}
	public static void close()
	{}
	public static void exit()
	{}

	//creates an empty data file
	public static void newFile()
	{
		DataFile newFile = new DataFile("");
		addDataFile(newFile);
	}

	//save the current file that is being edited.
	public static void save()
	{
		getCurrentFile().saveFile();
	}

	//save a copy of the file but don't set the copy to edit. 
	public static void saveCopy()
	{
		DataFile file = getCurrentFile();
		String tempPath = file.getFilePath();
		file.setFilePath("");
		file.saveFile();
		file.setFilePath(tempPath);
	}

	//save a copy of the file and file and set the newly saved file to be edited
	//NOTE: this does not save the work of the current file
	//to do this the user must click save first then save as.
	public static void saveAs()
	{
		DataFile file = getCurrentFile();
		file.setFilePath("");
		file.saveFile();
	}

	public static void newRobotSheet()
	{}

	public static DataFile getCurrentFile()
	{
		//later get the current file that is being edited
		return new DataFile("");
	}
	public static void showHelp()
	{}
	public static void showAbout()
	{}
	
	public static void addDataFile(DataFile newFile)
	{
		dataFiles.add(newFile);
	}
}
