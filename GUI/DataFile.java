import javax.swing.JFileChooser;
import java.util.Vector;
import javax.swing.JPanel;

//class implementing a binding between a file and its GUI display
public class DataFile extends JPanel
{
	private CSVFile file; 
	private JFileChooser fileChooser = new JFileChooser();
	private Vector<DataSheet> dataSheets;

	public DataFile(String path)
	{
		file = new CSVFile(path);
		dataSheets = new Vector<DataSheet>();
		if(path.equals("") || path == null) { setName("New file");return;}
		setName(file.getFileName());	
		//take the headers from the file and create data sheets from them
		if(file.getHeaders().size() < 1) return;
		for(int i=0;i<file.getHeaders().size();i++)
		{
			CSVDataHeader header = (CSVDataHeader)(file.getHeaders().elementAt(i));
			dataSheets.addElement(new DataSheet(header));
		}
	}

	//interface to open dialog box
	public String openFile()
	{
		return getPathDialog(false);
	}
	
	public void saveFile()
	{
		//if the current path of the file is non existant then 
		//open the save dialog and get one
		if(file.getPath().equals(""))
		{
			String path = getPathDialog(true);
			if(path.equals("")) return;
			file.setPath(path);
		}
		
		file.setHeaderList(getHeaderList());
		file.save();
		setName(file.getFileName());
		System.out.println(getName());
	}

	//mask the settting of a path for the file contained
	public void setFilePath(String newPath){file.setPath(newPath);}
	public String getFilePath(){return file.getPath();}	

	//get all the data from the GUI and return it in the proper format
	private Vector getHeaderList()
	{
		Vector headerList = new Vector();
		for(DataSheet datasheet : dataSheets)
			headerList.addElement(datasheet.getDataHeader());
		return headerList;
	} 

	//get a path through dialog box
	private String getPathDialog(boolean save)
	{
		int option = -1;
		if(save)
			option = fileChooser.showSaveDialog(null);
		else
			option = fileChooser.showOpenDialog(null);
		switch(option)
		{
			case JFileChooser.APPROVE_OPTION: 
				return fileChooser.getSelectedFile().getPath();
			default:
				return "";
		}
	}
}	
