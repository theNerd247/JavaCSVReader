import java.util.Vector;
import javax.swing.JPanel; 
import javax.swing.JLabel;
//gui container for each a header of data
/*
 *  ========Name,Name,Name======
 *  |dataType|dataType|dataType|
 *  |--------|--------|--------|
 *  | data 1 | data 2 | data 3 |
 *  |--------------------------|
 */
public class DataSheet extends JPanel
{
	CSVDataHeader header;
	JLabel title;
	public DataSheet(CSVDataHeader header)
	{
		super();
		this.header = header;
		title = new JLabel(header.getName());
	}
}	
