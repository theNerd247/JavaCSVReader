import java.util.Vector;

//class implementing data container for CSV data header
//data in headers is constant once created. 
public class CSVDataHeader
{
	//a vector is used to contain sub-vectors that will eventually hold data. 
	//the key to using vectors instead of a 2-D array is for  the purpose of 
	//resizing.
	private Vector data;
	//this is the descriptor that differentiates this header of data from other header patterns. 
	//from the example above this String should be "Name,Age,Number"
	private String[] names;
	private String title;

	public CSVDataHeader(String title, String[] names)
	{
		this.names = names;
		this.title = title;
		data = new Vector();
		//create empty vectors to contain the data
		for(int i=0;i<names.length;i++)
		{
			data.addElement(new Vector());
		}	
	}

	//add data to the end of a specified column
	public void appendToColumn(String new_data,int col)
	{
		((Vector)data.elementAt(col)).addElement(new_data);
	}

	public Vector getData(){return data;}
	public String getTitle(){return title;}
	public String[] getNames(){return names;}
}

