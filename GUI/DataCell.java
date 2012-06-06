import javax.swing.JTextField;

public class DataCell extends JTextField
{
	String contents;
	public DataCell(){this(new String())}
	public DataCell(String text)
	{
		contents = text;
		setSize(1,10);
	}
	public void setDataType(String type){dataType=type;}
	public String getDataType(){return dataType;}
}	

