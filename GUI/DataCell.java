import javax.swing.JTextField;

public class DataCell extends JTextField
{
	public DataCell(){this(new String());}

	public DataCell(String text)
	{
		super(text);
		setSize(1,10);
	}
}	

