/*
 * Copyright (c) 2012 theNerd247 (Noah Harvey)
 *
 * This product is licensed under the BSD 2-clause license
 * See LICENSE.txt for more details
 */

package JavaCSVReader;

import java.util.Vector;

/**
 * This class implements a 2D vector. 
 * <p>
 * The Vector2D class behaves as a normal vector would (as the data is contained
 * by vectors), however it's table properties behave in a unique manner. 
 * <br>The most important property of this class is that the table is ALWAYS 
 * rectangular. That is no column has a size greater than the other columns and 
 * the same with the rows. 
 * <br>Secondly items are not "removed". When an item is called to be removed it is 
 * instead replaced by a null object. This is done to uphold the first property.
 * <br>Thirdly, as an effect of the first property appending items to a column will
 * automatically append null objects at the end of all other columns (and a similiar
 * affect for appending to a row).
 * <br>However, do note: the Vector2D class AUTOMAGICALLY resizes when rows/columns/
 * items are removed. 
 *
 * @author theNerd247 (Noah Harvey)
 * @version 1.0
 */
public class Vector2D
{
	/** vector containing vectors (for the 2D affect) */
	private Vector items;

	/** the number of columns this table contains */
	private int width;

	/** the number of rows this table contains */
	private int height;
	
	/**
 	 * Constructs a new 2D vector with initial table size
     * of 0x0
     */
	public Vector2D(){this(0,0);}

	/**
  	 * Constructs a new 2D vector of initial vector-capacity
  	 * of the given dimensions
  	 *
  	 * @param width the number of columns the 2D vector is to contain
  	 * @param height the number of rows the 2D vector is to contain
  	 */ 
	public Vector2D(int width, int height)
	{
		this.width = width;
		this.height = height;
		//initialize the containing vector
		//and the column containers to 
		//have an increment size to that of 2
		items = new Vector(width,2);
		autosize(width,height);
	}

	/**
	 * Adds a new column of objects to the 2D vector
	 * <p>
	 * The new height of the table after adding the given
	 * column is determined by choosing the greatest value between
	 * <code>newColumn.size()</code> and <code>height</code>
	 * 
	 * @param newColumn the to add to the end of all the other columns 
	 */
	public void appendColumn(Vector newColumn)
	{
		items.addElement(newColumn);
		autosize(width+1,Math.max(newColumn.size(),height));
	}

	public int getTableHeight(){return height;}
	public int getTableWidth(){return width;}
	/**
	 * Adds a new row of objects to the 2D vector
	 * <p>
	 * The new row of the table after adding the given
	 * column is determined by choosing the greatest value between
	 * <code>newRow.size()</code> and <code>width</code>
	 * @param newRow the vector of objects to append to the 2D vector
	 */
	public void appendRow(Vector newRow)
	{
		autosize(Math.max(newRow.size(),width),height+1);
		for(int i=0;i<width;i++)
		{
			Vector col = (Vector)items.elementAt(i);
			if(i < newRow.size())
				col.setElementAt(newRow.elementAt(i),col.size()-1);
		}
	}

	/**
   	 * Insert a new object at the given index
   	 * <p>
   	 * The table will grow if needed to accommodate the given
   	 * indices
   	 * @param obj the Object to insert
   	 * @param x the column index 
   	 * @param y the row index
   	 */
	public void setItemAt(Object obj, int x, int y)
	{
		autosize(Math.max(width , x+1),Math.max(height, y+1));
		
		((Vector)items.elementAt(x)).setElementAt(obj,y);
	}

	/**
   	 * Insert a new object at the end of an EXISTING column
   	 *
   	 * @param obj the Object to insert
   	 * @param x the column index 
   	 * @exception ArrayIndexOutOfBoundsException - if x is not a
   	 * valid index 
   	 */
	public void appendToColumn(Object obj, int x)
	{
		setItemAt(obj,x,height);
	}

	/**
 	 * Insert a new object at the end of an EXISTING row
 	 * 
 	 * @param obj object to insert
 	 * @param y row index
 	 * @exception ArrayIndexOutOfBoundsException - if y is not a
 	 * valid index
 	 */ 
	public void appendToRow(Object obj,int y)
	{
		setItemAt(obj,width,y);
	}

	/**
 	 * Remove the object at the given index
 	 * <p>
   	 * <b>NOTE</b>: this method does NOT resize the 
   	 * column the item was contained. Instead the item
   	 * is simply replaced with a null object
   	 * @param x the column index
   	 * @param y the row index 
   	 * @exception ArrayIndexOutOfBoundsException - if an invalid index is given 
   	 */
	public void removeItemAt(int x , int y)
	{
		((Vector)items.elementAt(x)).setElementAt(null,y);
	}
	
	/**
     * Return the Object at the given index
     *
     * @param x column index
     * @param y row index
     * @return the object found at that index 
     * @exception ArrayIndexOutOfBoundsException - if an invalid index is given
     */
	public Object getItemAt(int x, int y)
	{
		return ((Vector)items.elementAt(x)).elementAt(y);
	}

	/**
     * Returns the table items that this 2D vector contains 
     *
     * @return A vector that contains other vectors than in turn contain items
     */ 
	public Vector getItems()
	{
		return items;
	}

	/**
 	 * Autogrow the table to a given size
 	 * <p><br><b>NOTE</b>
 	 * This will remove any items that are at index x,y or greater
 	 * 
 	 * @param x the width of the table
 	 * @param y the height of the table
 	 */
	public void autosize(int x, int y)
	{
		//set the proper width of the table
		items.setSize(x);
		width = items.size();
		height = y;

		//set the proper height of each column
		//create new Vectors for null columns 
		for(int i=0;i<width;i++)
		{
			Vector col = (Vector)items.elementAt(i);
			if(col == null)
				col = new Vector();
			col.setSize(y);
			items.setElementAt(col,i);
		}
	} 

	/**
 	 * Utility method that converts an array of Objects to a Vector
 	 *
 	 * @param objs the array of objects to convert
 	 * @return a vector representation of that array
 	 */ 
	public static Vector arrayToVector(Object[] objs)
	{
		Vector temp = new Vector(objs.length);
		for(int i=0;i<objs.length;i++)
		{
			temp.add(objs[i]);
		}
		return temp;
	} 

	/**
 	 * Utility method that converts a vector to an array of objects
 	 *
 	 * @param vector the vector to convert
 	 * @return an array of objects
 	 * @see Vector2D#arrayToVector(Object[])
 	 */
	public static Object[] vectorToArray(Vector vector)
	{
		Object[] objs = new Object[vector.size()];
		for(int i=0;i<objs.length;i++)
		{
			objs[i] = vector.elementAt(i);
		}
		return objs;
	}
}	
