
//data container for CSV data 
public class CSVData
{
	String[] header;
	Object[] data;
	int index;

	public CSVData(String[] header)
	{
		this.header = header;
		dataSize = header.length;
		data = new Object[header.length];
	}

	public void add(Object data)
	{
		this.data[index] = data;
		index++;
	}

	public void add(Object data, int index)
	{
		if(index < this.data.length)
			this.data[index] = data;
	}
}

