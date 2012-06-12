import java.io.InputStreamReader;
import java.io.File;
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
	private String name;

	private String[] mainHeader;
	private Vector headers;
	private String fileText;
	private String headerDelimiter="#";
	private String lineDelimiter=";";
	private String dataDelimiter=","; 

	public CSVFile(String path){this(path,"ASCII");}

	//create a new file
	public CSVFile(String path, String encoding)
	{
		this.path = path;
		this.encoding = encoding;
		headers = new Vector();
		//go ahead and read the file and set the data structures
		if(path.equals("") || path == null) return;
		readFileData();
		if(fileText == new String() || fileText == null) return;//if the file is empty then do nothing 
		parseFile(fileText);
	}

	//set the line and data delimiters to tell the parser
	//how to go through the CSV file
	public void setLineDelimiter(String d){lineDelimiter = d;}
	public void setDataDelimiter(String d){dataDelimiter = d;}
	public void setHeaderDelimiter(String d){headerDelimiter = d;}
	public String getLineDelimiter(){return lineDelimiter;}
	public String getHeaderDelimiter(){return headerDelimiter;}
	public String getDataDelimiter(){return dataDelimiter;}
	
	//return data
	public String getRawFileText(){return fileText;}
	public Vector getHeaders(){return headers;}
	public void setHeaders(Vector newHeaders){headers=newHeaders;}
	
	//allow external control over the file
	public String getPath(){return path;}
	public void setPath(String pth){path=pth;}
	public String getFileName(){return name;}
	
	//returns data from a given file
	private String readFileData()
	{
		String data = new String();
		try{
			File fl = new File(path);
			FileInputStream file = new FileInputStream(fl);
			name = fl.getName();
			in = new InputStreamReader(file);
			data = new String(new byte[0], encoding);
		
			//read data and check if it is the EOF character, if not then 
			//add the concatenate the data to the current data string using the 
			//given encoding typ
			while(true)
			{
				int raw = in.read();
				if(raw == -1) break;
				//read the value and convert to a a string by storing the value in a 1d,1cell byte array
				//then use that to create a new string and append to the current data. 
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
		fileText = data;
		return data;
	}

	//writes data to a file using the given encoding
	private void writeFileData(String data)
	{
		try
		{
			File fl = new File(path);
			FileOutputStream file = new FileOutputStream(fl);
			name = fl.getName();
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

	//write data from headers into the file
	public void save()
	{
		String text = createRawText();
		writeFileData(text);
	}
	
	//read through the raw data of a file and parse the data
	//into headers (held in CSVDataHeader class)
	public void parseFile(String rawInput)
	{
		//first split the raw input using new lines
		String[] lines = StringUtils.split(rawInput,"\n");
		int length = lines.length;
		CSVDataHeader workingHeader = null;
		//iterate through each line in the file and parse it according to the first character found
		for(int i=0;i<length;i++)
		{
			String temp = lines[i];
			//if it's a blank line then just skip it and go on
			if(temp.equals(new String()) || temp == null) continue;
			//first get the document delimiter data
			if(i==0)
			{
				parseFileMetaData(temp);	
				continue;
			}

			//determine what to do with the given line by the first character
			String key = temp.substring(0,1);
			if(key.equals(headerDelimiter))
			{
				//save the workingHeader before creating a new one
				if(workingHeader != null)
					addHeader(workingHeader);
				//if the first character is a header character then create a new header
				//based off of the info given. 
				workingHeader = createNewHeader(temp+"\n"+lines[i+1]);
				//because we are getting the first two lines of the header we need to skip
				//a line to prevent double processing lines
				i++;
				continue;
			}
			else if(key.equals(lineDelimiter))
			{
				workingHeader = parseHeaderData(workingHeader,temp);
			}
		}
		//add the last workingHeader to the list 
		if(workingHeader != null)
			headers.addElement(workingHeader);
	}	

	//parse the given raw string and return a header that contains the data
	private CSVDataHeader parseHeaderData(CSVDataHeader header, String rawText)
	{
		if(header == null) return header;
		String[] rawData = StringUtils.split(rawText.substring(1),dataDelimiter);
		String[] names = header.getNames();
		//after getting the rawData from the file and the current header for the 
		//dataHeader filter through the data - limiting only to the number of header 
		//data available. 
		for(int j=0;j<names.length;j++)
		{
			String dta = "null";
			if(j < rawData.length) dta = rawData[j];
			header.appendToColumn(dta,j);
		}
		return header;
	}

	//parse file metadata
	private void parseFileMetaData(String text)
	{
		String del = text.substring(0,1);
		String[] dels = StringUtils.rawSplit(text.substring(1),del);
		headerDelimiter=dels[0];
		lineDelimiter=dels[1];
		dataDelimiter=dels[2];
		//System.out.println(headerDelimiter+" "+lineDelimiter+" "+dataDelimiter);
	}
	
	//create new header from given text (should be two lines 
	//each beginning with the headerDelimiter
	private CSVDataHeader createNewHeader(String rawText)
	{
		//split the lines, then get the title from the first
		//and the data names from the second
		String[] headerLines = StringUtils.split(rawText,"\n");
		String title = headerLines[0].substring(1);
		String[] names = StringUtils.split(headerLines[1].substring(1),dataDelimiter);
		return new CSVDataHeader(title,names);	
	}

	//add new header to the files header container
	public void addHeader(CSVDataHeader header){if(header != null) headers.addElement(header);}

	//removes the first header whos title matches the given title
	public void removeHeader(String title)
	{
		if(title == null || title.equals("")) return;
		for(int i=0;i<headers.size();i++)
		{
			if(title.equals(((CSVDataHeader)headers.elementAt(i)).getTitle()))
			{
				headers.removeElementAt(i); 
				break;
			}
		}
	}
	
	//set the entire list of headers to the given list
	public void setHeaderList(Vector newHeaders){headers= newHeaders;}

	//traverse through the headers  creating CSV file text
	private String createRawText()
	{
		//first create the delimiters to default. 
		String fileData= "|"+headerDelimiter+"|"+lineDelimiter+"|"+
							dataDelimiter+"|";
		for(int k=0;k<headers.size();k++)
		{
			//get the data header for the given sheet and translate the text back into 
			//the proper format using the files delimiters
			CSVDataHeader dataHeader = (CSVDataHeader)headers.elementAt(k);
			//traverse through the names of the header and create header metadata text 
			String headerText = "\n"+headerDelimiter+dataHeader.getTitle()+"\n"+headerDelimiter;
			String[] names = dataHeader.getNames();
			for(int i=0;i<names.length;i++)
			{
				headerText+=names[i];
				if(i+1 < names.length) headerText+=dataDelimiter;
			}
			//traverse through the data of the header and create txt
			String dataText = "\n";
			Vector dta = dataHeader.getData();
			int colWidth = dta.size();
			int rowHeight = ((Vector)(dta.elementAt(0))).size();
			for(int i=0;i<rowHeight;i++)
			{
				dataText+=lineDelimiter;
				for(int j=0;j<colWidth;j++)
				{
					String dat = (String)((Vector)dta.elementAt(j)).elementAt(i);
					dataText+=dat;
					if(j+1 < colWidth) dataText+=dataDelimiter;
				}
				dataText+="\n";
			}
			fileData+=headerText+dataText;
		}
		return fileData;
	}
}

//later add method for converting a 2-D Vector into a raw string for writing to a file
