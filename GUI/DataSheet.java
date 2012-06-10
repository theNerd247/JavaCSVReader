import java.util.Vector;
import javax.swing.JPanel; 
import javax.swing.JTextField;
//gui container for each a header of data
/*
 *  =========TITLE==============
 *  ========Name,Name,Name======
 *  |--------|--------|--------|
 *  | data 1 | data 2 | data 3 |
 *  |--------------------------|
 */
public class DataSheet extends JPanel
{
	private CSVDataHeader header;
	private JTextField title;
	private Vector<JTextField> names;
	private Vector<Vector<DataCell>> dataCells;

	public DataSheet(CSVDataHeader header)
	{
		super();
		this.header = header;
		title = new JTextField(header.getTitle());
		names = new Vector<JTextField>();
		dataCells = new Vector<Vector<DataCell>>();

		//create gui label for header names
		for(String name : header.getNames())
			names.add(new JTextField(name));

		//create data cells for each data given
		for(int i=0;i<header.getData().size();i++)
		{
			Vector col = (Vector)(header.getData().elementAt(i));
			Vector<DataCell> temp = new Vector<DataCell>();
			for(int j=0;j<col.size();j++)
			{
				String data = (String)(col.elementAt(j));
				temp.add(new DataCell(data));
				dataCells.add(temp);
			}
		}
	}

	//return the data that that this sheet contains
	public CSVDataHeader getDataHeader()
	{
		String[] nams = new String[names.size()];
		for(int i=0; i<nams.length;i++)
			nams[i]=names.elementAt(i).getText();

		CSVDataHeader newHeader = new CSVDataHeader(title.getText(),nams);
		//itterate through the data cells and collect data
		//creating a new header as we itterate
		for(Vector<DataCell> col : dataCells)
		{	
			int i=0;
			for(DataCell cell : col)
			{
				newHeader.appendToColumn(cell.getText(),i);
				i++;
			}
		}
		header = newHeader;
		return header;
	} 
}	
