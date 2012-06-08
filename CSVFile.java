import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.Vector;

public class CSVFile
{
	private InputStreamReader in;
	private OutputStreamWriter out;
	private String encoding;
	private String path;

	private String[] mainHeader;
	private Vector data;
	private String fileData;
	private String headerDelimiter="#";
	private String lineDelimiter=";";
	private String dataDelimiter=","; 

	public CSVFile(String path){this(path,"ASCII");}

	//create a new file
	public CSVFile(String path, String encoding)
	{
		this.path = path;
		this.encoding = encoding;
		data = new Vector();
		//go ahead and read the file and set the data structures
		readFileData();
		parseFile(fileData);
	}

	//set the line and data delimiters to tell the parser
	//how to go through the CSV file
	public void setLineDelimiter(String d){lineDelimiter = d;}
	public void setDataDelimiter(String d){dataDelimiter = d;}
	public void setHeaderDelimiter(String d){headerDelimiter = d;}

	//return data
	public String getRawData(){return fileData;}
	public Vector getData(){return data;}
	public String[] getHeader(){return mainHeader;}

	//returns data from a given file
	private String readFileData()
	{
		String data = new String();
		try{
			FileInputStream file = new FileInputStream(path);
			in = new InputStreamReader(file);
			data = new String(new byte[0], encoding);
		
			//read data and check if it is the EOF character, if not then 
			//add the concatenate the data to the current data string using the 
			//given encoding type
		
			while(true)
			{
				int raw = in.read();
				if(raw == -1) break;
				byte value = (new Integer(raw)).byteValue();
				byte[] temp = {value};
				data = data+new String(temp,encoding);
			}

			//close stream when done
			in.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage()); return new String();
		}
		fileData = data;
		return data;
	}

	//writes data to a file using the given encoding
	public void writeFileData(String data)
	{
		try
		{
			FileOutputStream file = new FileOutputStream(path);
			out = new OutputStreamWriter(file,encoding);
			out.flush(); //flush the stream just to be sure there is nothing "clogging" it
			out.write(data,0,data.length());
			out.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage()); return;
		}
	}

	//read through the raw data of a file and parse the data
	//into headers (held in CSVDataHeader class)
	public void parseFile(String rawInput)
	{
		//first split the raw input using new lines
		String[] lines = StringUtils.split(rawInput,"\n");
		int length = lines.length;
		CSVDataHeader currentHeader = null;
		for(int i=0;i<length;i++)
		{
			String temp = lines[i];
			if(temp.equals(new String()) || temp == null) continue;
			//first get the document delimiter data
			if(i==0)
			{
				String del = temp.substring(0,1);
				String[] dels = StringUtils.rawSplit(temp.substring(1),del);
				headerDelimiter=dels[0];
				lineDelimiter=dels[1];
				dataDelimiter=dels[2];
				System.out.println(headerDelimiter+" "+lineDelimiter+" "+dataDelimiter);
				continue;
			}
			//determine what to do with the given line by the first character
			String key = temp.substring(0,1);
			if(key.equals(headerDelimiter))
			{
				//save the currentHeader before creating a new one
				if(currentHeader != null) data.add(currentHeader);
				//if the first character is a header character then create a new header
				//based off of the info given. 
				String[] rawHeader = StringUtils.split(temp.substring(1),dataDelimiter);
				String[] dataTypes = new String[rawHeader.length];
				String name = new String();
				for(int j=0;j<rawHeader.length;j++)
				{
					int ind = rawHeader[j].indexOf(")");
					name+= rawHeader[j].substring(ind+1);
					if(j<rawHeader.length-1) name+=",";
					dataTypes[j] = rawHeader[j].substring(1,ind);
				}
				currentHeader = new CSVDataHeader(dataTypes,name);
			}
			else if(key.equals(lineDelimiter))
			{
				if(currentHeader == null) continue;
				String[] rawData = StringUtils.split(temp.substring(1),dataDelimiter);
				String[] header = currentHeader.getHeader();
				//after getting the rawData from the file and the current header for the 
				//dataHeader filter through the data - limiting only to the number of header 
				//data available. 
				for(int j=0;j<header.length;j++)
				{
					currentHeader.addRaw(rawData[j],header[j]);
				}
			}
		}
		data.add(currentHeader);
	}	
}
