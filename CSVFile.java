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

	private String[] header;
	private String[][] data;
	private String headerDelimiter="#";
	private String lineDelimiter=";";
	private String dataDelimiter=","; 

	public CSVFile(String path){this(path,"ASCII");}

	//create a new file
	public CSVFile(String path, String encoding)
	{
		this.path = path;
		this.encoding = encoding;
	}

	//set the line and data delimiters to tell the parser
	//how to go through the CSV file
	public void setLineDelimiter(String d){lineDelimiter = d;}
	public void setDataDelimiter(String d){dataDelimiter = d;}
	public void setHeaderDelimiter(String d){headerDelimiter = d;}

	//return data duh...
	public String[][] getData(){return data;}

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

	//reads the data from the file and parses the data into usable stuff
	public void readFile()
	{
		//get the file data and check to see if it contains no data.
		//return if it does not
		String rawData = noNewLine(readFileData());
		if(rawData.equals(new String())) return;
		
		//first extract the header from the data
		String headerData = rawSplit(rawData,headerDelimiter)[0];
		header = split(headerData,dataDelimiter);
		
		int start = rawData.lastIndexOf(headerDelimiter)+1;
		rawData = rawData.substring(start);
		
		//to break down the data we must first get the lines of data...
		String[] lines = rawSplit(rawData,lineDelimiter);
		
		//then the tricky part: The header is used to tell the program how to break up the data
		//The number of items in the header is the number of arrays of data that we are going 
		//to read. Later add the specific data type of the head, or multiple heads
		
		data = new String[header.length][lines.length];
		for(int i=0;i<lines.length;i++)
		{
			String[] sub = split(lines[i],dataDelimiter);
			for(int j=0;j<header.length;j++)
			{
				data[j][i]=sub[j];
			}
		}
	}

	private String noNewLine(String input)
	{
		String[] choppedString = split(input,"\n");
		String s = new String();
		int l = choppedString.length;

		for(int i=0;i<l;i++)
		{
			s=s+choppedString[i];
		}

		return s;
	}

	//overloads of split
	//split, save extra data defined past last delimiter, start at begining of string
	private String[] split(String input, String delimiter){return split(input,delimiter,true,0);}
	//as above but start at different index
	private String[] split(String input, String delimiter, int start){return split(input,delimiter,true,start);}
	//split but DON'T return data past the last delimiter
	private String[] rawSplit(String input, String delimiter){return split(input,delimiter,false,0);}
	
	//used to scan through the given text and return all data before or inbetween the given delimiter
	//the last item in the array returned is the rest of the String AFTER the last delimiter if withEnd is true
	private String[] split(String str, String delimiter,boolean withEnd,int initIndex)
	{
		String input = str;
		if(input == null || input.equals(new String()) || input == "") return new String[0];

		//create a buffer array for the worst case scenario amount of data
		Vector dataBuffer = new Vector();
		int index = 0;
		
		if(initIndex > 0)
			input = input.substring(initIndex);

		//take the current string, split it into two strings: the next piece of data (before delimiter)
		//and the rest of the input string (after the delimiter). Set the input to the
		//later of the two strings and repeat.  
		while(true)
		{
			if(input == null) break;
			int cutIndex = input.indexOf(delimiter);
			if(cutIndex == -1)
			{
				if(withEnd)
					dataBuffer.add(input);
				break;
			}
			String sub1 = input.substring(0,cutIndex);
			String sub2 = input.substring(cutIndex+1);

			dataBuffer.add(sub1);
			input = sub2;
		}	
		String[] d = new String[dataBuffer.size()];
	
		int l = dataBuffer.size();
		for(int i=0;i<l;i++)
		{
			d[i]=(String)dataBuffer.elementAt(i);
		}
		return d;
	}

	public static void main(String[] args)
	{
		CSVFile file = new CSVFile("/home/boys/Documents/Noah/src/GIT/JavaCSVReader/test.txt");
		
		file.readFile();
		String[][] data = file.getData();

		for(String[] i : data)
		{
			System.out.println();
			for(String d : i)
				System.out.print(d+",");
		}
		
		System.out.println("end");
	}
}
