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
			data.add(i,new Vector());
		}	
	}

	//add data to the end of a specified column
	public void appendToColumn(String new_data,int col)
	{
		((Vector)data.elementAt(col)).add(new_data);
	}
	
/*             COMMENTED OUT DUE TO NO USE
	//append data to the specified 2-d index
	public void addData(String new_data, int row, int col)
	{
		((Vector)data.elementAt(col)).insertElementAt(new_data,row);
	}
	//add another header column
	public void newDataColumn(String name)
	{
		//manually grow the names
		String[] temp = new String[names.length+1];
		for(int i=0;i<names.length;i++)
			temp[i] = names[i];
		temp[temp.length-1] = name;
		data.add(new Vector());
	}
*/
	public Vector getData(){return data;}
	public String getTitle(){return title;}
	public String[] getNames(){return this.names;}

	//for testing this class
	public static void main(String[] args)
	{
		String[] nams = {"Bob","Joe","Larry"};
		CSVDataHeader header = new CSVDataHeader("TEST",nams);
		header.appendToColumn("Tomatoe",0);
		header.appendToColumn("WEIRD",1);
		header.appendToColumn("Cucumber",2);
	
		Vector data = header.getData();
	
		System.out.println(header.getTitle());
		String[] names = header.getNames();
		for(String i : names)
			System.out.print(i+",");
		System.out.print("\n");
		String d1 = (String)(((Vector)data.elementAt(0)).elementAt(0));
		System.out.println(d1);
	}
}

