/*
 * Copyright (c) 2012, Noah Harvey
 *
 * This project is licensed under the BSD 2-Clause license.
 * See LICENSE.txt for a copy.
 */

package JavaCSVReader;

import java.io.InputStreamReader;
import java.io.File;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.Vector;

/**
 * This class implements a container for holding,reading, and writing 
 * CSV data
 *
 * @author Noah Harvey
 * @version 1.0
 */
public class CSVFile
{
	/** the encoding type of the file 
 	 *  @see java.io.OutputStreamWriter
 	 */
	private String encoding;

	/** the path of the file to read/write data*/
	private String path;

	/** the name of the file (including any file extension) */
	private String name;

	/** the data headers contained in the file. 
     * <p> <br>This can be thought of as the spread sheets that are found in a file. 
     * <br>Each vector contains an Object of type CSVDataHeader.
     * @see CSVDataHeader
     */
	private Vector headers;

	/** the raw text that is found in the file when reading/writing*/
	private String rawFileText;

	//defalut delimiters for a file
	//when writing a new file these are the delimiters used
	private String headerDelimiter="#";
	private String lineDelimiter=";";
	private String dataDelimiter=","; 

	/**
     * Constructs a new file that is ascii encoded
     *
     * @param path the path of the file to create
     * @see CSVFile#CSVFile(String path,String encoding)
     */ 
	public CSVFile(String path){this(path,"ASCII");}

	/**
 	 * Constructs a new CSVFile
 	 *
 	 * @param path the path of the file to create 
 	 * @param encoding the encoding of the text in the file
 	 */ 
	public CSVFile(String path, String encoding)
	{
		this.path = path;
		this.encoding = encoding;
		headers = new Vector();
		//go ahead and read the file and create the data headers as 
		//needed
		rawFileText = readFileData();
		parseFile(rawFileText);
	}

	/** 
 	 * Sets the delimiter to use when parsing for a row of data 
 	 * 
 	 * @param d the new delimiter to use
 	 */ 
	public void setLineDelimiter(String d){lineDelimiter = d;}

	/** 
 	 * Sets the delimiter to use when parsing a row for data 
 	 * 
 	 * @param d the new delimiter to use
 	 */
	public void setDataDelimiter(String d){dataDelimiter = d;}

	/** 
 	 * Sets the delimiter to use when parsing a header of data 
 	 * 
 	 * @param d the new delimiter to use
 	 */
	public void setHeaderDelimiter(String d){headerDelimiter = d;}

	/** @return the line delimiter of this file*/
	public String getLineDelimiter(){return lineDelimiter;}

	/** @return the header delimiter of this file */
	public String getHeaderDelimiter(){return headerDelimiter;}

	/** @return the data delimiter of this file */
	public String getDataDelimiter(){return dataDelimiter;}
	
	/** @return the list of headers this file contains */
	public Vector getHeaders(){return headers;}

	/**
 	 * Sets the entire list of headers to the given list
 	 * <br><b>CAUTION:</b>This removes any previously contained data 
 	 * that the data headers of this file contain
 	 *
 	 * @param newHeaders the new set of headers to use
 	 */
	public void setHeaderList(Vector newHeaders){headers=newHeaders;}
	
	/**@return the path of this file */
	public String getPath(){return path;}

	/**@param pth the new path to use for this file*/
	public void setPath(String pth){path=pth;}

	/**@return the name of this file*/
	public String getFileName(){return name;}

	/**@param newName the new name of this file */
	public void setFileName(String newName){ name = newName;}
	
	/**
 	 * Reads the text contained in a file based on the file's encoding. 
 	 *
 	 * @return the raw text contained in the file
 	 * @see CSVFile#path for details on where to read the data from
 	 */ 
	private String readFileData()
	{
		if(path.equals("") || path == null) return null;
		String data = new String();
		try{
			File fl = new File(path);
			FileInputStream file = new FileInputStream(fl);
			name = fl.getName();
			InputStreamReader in = new InputStreamReader(file);
			data = new String(new byte[0], encoding);
			//read data and check if it is the EOF character, if not then 
			//add the concatenate the data to the current data string using the 
			//given encoding typ
			while(true)
			{
				int raw = in.read();
				if(raw == -1) break;
				//read the value and convert to a a string by wrapping it in a byte array
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
			//possibly create a logging class to cache error messages
			System.out.println(e.getMessage()); return new String();
		}
		return data;
	}

	/**
 	 * Writes data to the file.
 	 *
 	 * @param data the String of data to write to the file
 	 */ 
	private void writeFileData(String data)
	{
		try
		{
			File fl = new File(path);
			//open the file to write (overwrite any data in the file)
			FileOutputStream fileStream = new FileOutputStream(fl,false);
			name = fl.getName();
			OutputStreamWriter out = new OutputStreamWriter(fileStream,encoding);
			out.write(data,0,data.length());
			out.flush();
			out.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage()); return;
		}
	}

	/**
 	 * Saves raw text to the file based on the data contained in the headers list.
 	 *
 	 * @see CSVFile#createRawText() 
 	 * @see CSVFile#writeFileData(String)
 	 */ 
	public void save()
	{
		writeFileData(createRawText());
	}
	
	/**
 	 * Parse through the given text saving the given data into CSVDataHeaders
	 *<p>
	 * <br>This is done by reading through each line in the text (denoted by the value "\n")
	 * and parsing the data according to the first character in the line. If the character
	 * is one of the three delimiters then parse it accordingly. If it not skip it and 
	 * move on. 
	 *
	 * @param rawInput the raw text to parse - generally the value returned by readFileData().
	 * @see CSVFile#readFileData()
	 */
	public void parseFile(String rawInput)
	{
		if(rawFileText.equals("") || rawFileText == null) return;		

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

	/** 
 	 * Parse the given text and append it to the appropriate columns
 	 * in the given CSVDataHeader.
 	 *
 	 * @param header the header to append the data to
 	 * @param rawText the raw text to parse
 	 * <p>
 	 * Should be a line of raw text from the file in proper JavaCSVReader format see README.txt
 	 *
 	 * @return the given header with the data appended to it
 	 * @see Vector2D#appendToColumn(Object,int) 
 	 */ 
	private CSVDataHeader parseHeaderData(CSVDataHeader header, String rawText)
	{
		if(header == null) return header;
		String[] rawData = StringUtils.split(rawText.substring(1),dataDelimiter);
		String[] names = header.getNames();
		//after getting the rawData from the file and the current header for the 
		//dataHeader filter through the data - limiting only to the number of header 

		header.appendRow(Vector2D.arrayToVector(rawData));
		return header;
	}

	/**
  	 * Parse the given text to determine the delimiters for parsing the file
  	 * <p>
  	 *<br>view the README.txt for more details on file and text formatting
	 *
  	 * @param text the raw text to use to parse for the delimiters.
  	 * <br><pre>This is normally the FIRST line in a file
  	 */ 
	private void parseFileMetaData(String text)
	{
		String del = text.substring(0,1);
		String[] dels = StringUtils.rawSplit(text.substring(1),del);
		headerDelimiter=dels[0];
		lineDelimiter=dels[1];
		dataDelimiter=dels[2];
	}
	
	/**
	 * Create new header from given text (should be two lines) each beginning with the headerDelimiter.
	 * <p>
	 * <br>view the README.txt for more details on file and text formatting
	 *
	 * @param rawText the rawText to parse for creating the new CSVDataHeader
	 * @return the new CSVDataHeader created based on the given text
	 */
	private CSVDataHeader createNewHeader(String rawText)
	{
		//split the lines, then get the title from the first
		//and the data names from the second
		String[] headerLines = StringUtils.split(rawText,"\n");
		String title = headerLines[0].substring(1);
		String[] names = StringUtils.split(headerLines[1],dataDelimiter,1);
		return new CSVDataHeader(title,names);	
	}

	/**@param header the new header to add to the list of headers this file contains */
	public void addHeader(CSVDataHeader header){if(header != null) headers.addElement(header);}

	/**@param title the title of the header to remove from the list of headers this file contains */
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

	/**
 	 *@param title the title of the header to get 
 	 *@return the CSVDataHeader that matches the given title 
 	 */
	public CSVDataHeader getHeader(String title)
	{
		for(int i=0;i<headers.size();i++)
		{
			CSVDataHeader header = (CSVDataHeader)headers.elementAt(i);
			if(header.getTitle().equals(title))
				return header;
		}
		
		return null;
	}
	
	/**
	 * Traverse through the list of headers and create the raw text for a file
	 * based on the data contained in the headers. 
	 *
	 * @return the raw text created
	 * @see CSVFile#save()
	 */
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
				//make sure we don't add commas after the last bit of data
				if(i+1 < names.length) headerText+=dataDelimiter;
			}
			//traverse through the data of the header and create txt
			String dataText = "\n";
			int colWidth = dataHeader.getTableWidth();
			int rowHeight = dataHeader.getTableHeight();
			for(int i=0;i<rowHeight;i++)
			{
				dataText+=lineDelimiter;
				for(int j=0;j<colWidth;j++)
				{
					dataText+=(String)dataHeader.getItemAt(j,i);
					if(j+1 < colWidth) dataText+=dataDelimiter;
				}
				dataText+="\n";
			}
			fileData+=headerText+dataText;
		}
		return fileData;
	}
}
