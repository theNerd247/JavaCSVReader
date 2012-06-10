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
		if(path.equals("") || path == null) return;
		//take the headers from the file and create data sheets from them
		for(int i=0;i<file.getHeaders().size();i++)
		{
			CSVDataHeader header = (CSVDataHeader)(file.getHeaders().elementAt(i));
			dataSheets.add(new DataSheet(header));
		}
	}

	public void saveFile()
	{
		//if the current path of the file is non existant then 
		//open the save dialog and get one
		if(file.getPath().equals(""))
		{
			String path = getPathDialog();
			if(path.equals("")) return;
			file.setPath(path);
		}
		file.writeFileData(getDataSheetText());
	}

	//mask the settting of a path for the file contained
	public void setFilePath(String newPath){file.setPath(newPath);}
	public String getFilePath(){return file.getPath();}	

	//get all the data from the GUI and return it in the proper format
	private String getDataSheetText()
	{
		String fileData= "|"+file.getHeaderDelimiter()+"|"+file.getLineDelimiter()+"|"+
							file.getDataDelimiter()+"|";
		for(DataSheet datasheet : dataSheets)
		{
			//get the data header for the given sheet and translate the text back into 
			//the proper format using the files delimiters
			CSVDataHeader dataHeader = datasheet.getDataHeader();
			String headerText = file.getHeaderDelimiter()+dataHeader.getTitle()+"\n"+file.getHeaderDelimiter();
			String[] names = dataHeader.getNames();
			for(int i=0;i<names.length;i++)
			{
				headerText+=names[i];
				if(i+1 != names.length) headerText+=file.getDataDelimiter();
			}
			String dataText = "\n";
			Vector dta = dataHeader.getData();
			for(int i=0;i<dta.size();i++)
			{
				Vector col = (Vector)(dta.elementAt(i));
				dataText+=file.getLineDelimiter();
				for(int j=0;j<col.size();j++)
				{
					String dat = (String)(col.elementAt(j));
					dataText+=dat;
					if(j+1 != col.size()) dataText+=file.getDataDelimiter();
					j++;
				}
				dataText+="\n";
			}
			fileData+=headerText+dataText;
		}
		return fileData;
	}

	//get a path through dialog box
	private String getPathDialog()
	{
		int option = fileChooser.showSaveDialog(null);
		switch(option)
		{
			case JFileChooser.APPROVE_OPTION: 
				return fileChooser.getSelectedFile().getPath();
			default:
				return "";
		}
	}
}	
