import java.util.Vector;

/// Class implementing a data container for CSV data header
public class CSVDataHeader
{
	//the header variable contains the blue print for the data types 
	//that this object is to hold. For example if (String)Name,(int)Age,(double)Number
	//are the raw data of a CSV file then the blue print should go as follows
	//header[0] = "String"; header[1] = "int"; header[2] = "double";
	private String[] header;
	//this vector is to contain other vectors to form a 2-d Array pattern. 
	private Vector data;
	//this is the descriptor that differenciates this header of data from other header patterns. 
	//from the example above this String should be "Name,Age,Number"
	private String name;

	public CSVDataHeader(String[] header, String name)
	{
		this.header = header;
		this.name = name;
		data = new Vector();
		for(int i=0;i<header.length;i++)
		{
			data.add(i,new Vector());
		}
		
	}

	//add data to the specified index
	public void add(Object data,int index)
	{
		((Vector)this.data.elementAt(index)).add(data);
	}

	//if no index was given then guess where the data is supposed to go 
	//by getting the data class type and matching it with a header blue print
	public void add(Object data,String type)
	{
		int index = 0;
		for(int i=0;i<header.length;i++)
		{
			if(type.equals(header[i])){ add(data,i); break;}
		}	  
	}

	public void add(int value){	add(new Integer(value),"int");}	
	public void add(long value){add(new Long(value),"long");} 
	public void add(boolean value){add(new Boolean(value),"boolean");}
	public void add(float value){add(new Float(value),"float");} 
	public void add(double value){add(new Double(value),"double");}
	public void addString(String value){add(value,"String");}

	//convert a raw string to an object that contains its value and
	//insert it into the header
	public void addRaw(String rawData, String headerName)
	{
		try{
			if(headerName.equals("String"))
				addString(new String(rawData));
			else if(headerName.equals("int"))
				add((Integer.parseInt(rawData)));
			else if(headerName.equals("double"))
				add((Double.parseDouble(rawData)));
			else if(headerName.equals("long"))
				add((Long.parseLong(rawData)));
			else if(headerName.equals("float"))
				add((Float.parseFloat(rawData)));
			else if(headerName.equals("bool"))
				add((Boolean.parseBoolean(rawData)));
			else
				return;
		}
		catch(Exception e){System.out.println(e.getMessage());}
	}

	public Vector getData(){return data;}
	public String[] getHeader(){return header;}
	public String getName(){return name;}
	
}

