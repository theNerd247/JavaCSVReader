/*
 * Copyright (c) 2012 theNerd247 (Noah Harvey)
 *
 * This product is licensed under the BSD 2-clause license
 * See LICENSE.txt for more details
 */

package JavaCSVReader;

import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.File;
import java.io.OutputStreamWriter;
import java.io.OutputStream;

import java.io.IOException;

/**
 *
 * @author theNerd247 (Noah Harvey)
 */
public class FileIO
{
	/**
 	 * Reads raw data from a given file with a certain encoding. <b>Instead of passing a 
 	 * file path an InputStream is used.</b>
 	 *
 	 * @param encoding the data encoding of the file (must be supported by Java)
 	 * @param inputStream the InputStream to read the data from
 	 */
	public static String readFileData(InputStream inputStream,String encoding) throws IOException
	{
		String data = new String();
		InputStreamReader in = new InputStreamReader(inputStream);
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

		return data;
	}

	/**
 	 * Writes the given string to a file. <b>Instread of passing a file
 	 * path, an OutputStream is used</b>
 	 *
 	 * @param data the string to write
 	 * @param outStream the OutputStream to use to write the data 
 	 * @param encoding the encoding type of the file
 	 */ 
	public static void writeFileData(String data, OutputStream outStream, String encoding) throws IOException
	{
		OutputStreamWriter out = new OutputStreamWriter(outStream,encoding);
		out.write(data,0,data.length());
		out.flush();
		out.close();
	}
}
