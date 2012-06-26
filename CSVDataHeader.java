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
public class CSVDataHeader extends Vector2D
{
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
	}

	/**
     * Returns the 2D vector containing the data of the CSVDataHeader
     *
     * @return Vector the 2D vector containing the data
     * @see CSVDataHeader#data 
     */ 
	public Vector getData(){return getItems();}

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

