import java.util.Vector;
import javax.swing.JTable;

//gui container for each a header of data
public class DataSheet
{
	CSVDataHeader header;
	JTable table;
	TableModel model;
	public DataSheet(CSVDataHeader header)
	{
		this.header = header;
		String[] types = header.getHeder();
		Vector v = new Vector(types.length);
		for(String i : types)
			v.add(i);
		table = new JTable(header.getData(),v);
		model = table.getModel();
	}
	
	public void removeHeader(int col)
	{
		
	}
	public void insertHeaderColumn(String Name, String type)
	{
		data.newHeader(type,Name);
		table.
	}

	
}	
