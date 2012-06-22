/**
 * Copyright (c) 2012, Noah Harvey
 *
 * This project is licensed under the BSD 2-Clause license.
 * See LICENSE.txt for a copy.
 */

package JavaCSVReader;

import java.util.Vector;

/**
 * This class implements a "spreadsheet" which contains data in
 * columns and rows. Once data is added to a header it can 
 * not be removed
 *
 * @author Noah Harvey
 * @version 0.1
 */
public class CSVDataHeader
{
	/**
	 * A 2D vector to contain the data
	 * 
	 * <br>contains sub-vectors that hold the data.
	 * Each subvector can be thought of as a column of data. 
	 */
	private Vector data;

	/**
	 * The names of the columns of data
	 */
	private String[] names;

	/**
     * The title of the dataheader
     */	 
	private String title;

	/**
 	 * Creates a new DataHeader based on the given title and initial column rows
 	 * 
 	 * @param title the title of the new CSVDataHeader
 	 * @param names the names of the columns of the new CSVDataHeader
 	 */ 
	public CSVDataHeader(String title, String[] names)
	{
		this.names = names;
		this.title = title;
		data = new Vector();
		//create empty vectors to contain the data
		for(int i=0;i<names.length;i++)
		{
			data.addElement(new Vector());
		}	
	}

	/**
     * add data to the end of a specified column
     * 
     * @param new_data the string value of the data to append to the column
     * @param col the index of the column to append the data to
     * @exception ArrayIndexOutOfBoundsException
     * 				if col is not in the range <code>0</code>
     * 				to <code>data.length-1</code>
     */
	public void appendToColumn(String new_data,int col)
	{
		((Vector)data.elementAt(col)).addElement(new_data);
	}

	/**
     * Returns the 2D vector containing the data of the CSVDataHeader
     *
     * @return Vector the 2D vector containing the data
     * @see CSVDataHeader#data 
     */ 
	public Vector getData(){return data;}

	/**
	 * Returns the title of the CSVDataHeader
	 *
	 * @return String title of the data header
	 */
	public String getTitle(){return title;}

	/**
     * Returns an array of the column names 
     *
     * @return String[] the array of column names
     */
	public String[] getNames(){return names;}
}

