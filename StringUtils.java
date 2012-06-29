/*
 * Copyright (c) 2012, Noah Harvey
 *
 * This project is licensed under the BSD 2-Clause license.
 * See LICENSE.txt for a copy.
 */

package JavaCSVReader;

import java.util.Vector;

/**
 *class to extend the utilities of the String class.
 * <p>NOTE: this class may contain methods that have nothing to do with the JavaCSVReader lib. 
 *    : extra methods are kept for robotics team's usage in their FRC code.
 *@author Noah Harvey
 *@version 1.0
 */
public class StringUtils
{
	/**
	 *overload of {@link StringUtils#split(String,String,boolean,int)}
	 *
	 * @param input the string to split
	 * @param delimiter the delimiter to use to split the string
	 * @return split, and return extra data defined past last delimiter, start at begining of string
	 * @see StringUtils#split(String,String,boolean,int)
	 */
	public static String[] split(String input, String delimiter){return split(input,delimiter,true,0);}
	
	/**
	 * overload of {@link StringUtils#split(String,String,boolean,int)}
	 * 
	 * @param input the string to split
	 * @param delimiter the delimiter to use to split the string
	 * @param start the index to start splitting the string at
	 * @return split, and return extra data defined past last delimiter, start at begining of string
	 * @see StringUtils#split(String,String,boolean,int)
	 */
	public static String[] split(String input, String delimiter, int start)
	{
		return split(input.substring(start),delimiter);
	}

	/**
	 * overload of {@link StringUtils#split(String,String,boolean,int)}
	 * 
	 * @param input the string to split
	 * @param delimiter the delimiter to use to split the string
	 * @return split, but DO NOT return extra data defined past last delimiter, start at begining of string
	 * @see StringUtils#split(String,String,boolean,int)
	 */
	public static String[] rawSplit(String input, String delimiter)
	{
		return split(input,delimiter,false,0);
	}

	/**
	 * Split the data found only in between the given delimiter 
	 *
	 * @param input the string to split
	 * @param delimiter the delimiter to use to split the string
	 * @return array of data found in between delimiters 
	 */
	public static String[] tagSplit(String input, String delimiter)
	{
		Vector buffer = new Vector();
		while(true)
		{
			//find the begin index of the first set of encasing delimiters
			int begin = input.indexOf(delimiter);
			int end = input.indexOf(delimiter,begin+1);
			
			if(begin == -1 || end == -1) break; 			

			buffer.addElement(input.substring(begin+1,end));
			input = input.substring(end+1);
		}
		int size = buffer.size();
		String[] d = new String[size];
		for(int i=0;i<size;i++)
		{
			d[i] = (String)buffer.elementAt(i);
		}
		return d;
	}
	
	/**
 	 * Same as tagsplit but return the data that is OUTSIDE of the given delimiters
 	 *
 	 * @param input the text to split
 	 * @param delimiter the delimiter to use
 	 * @return array of data found outside of the delimiters
 	 * @see StringUtils#tagSplit(String,String)
 	 */ 
	public static String[] invertTagSplit(String input, String delimiter)
	{
		int start = input.indexOf(delimiter);
		return tagSplit(input.substring(start+1),delimiter);
	}

	/**
 	 * Split the given string around the delimiter given.  
	 * 
	 * @param str the string to split
	 * @param delimiter the delimiter to use to split the string
	 * @param withEnd return data that is found past the last delimiter?
	 * @param initIndex the starting index to begin splitting around
	 * @return array of data found around the delimiters 
	 * @see StringUtils#split(String,String,boolean,int)
	 */	
	public static String[] split(String str, String delimiter,boolean withEnd,int initIndex)
	{
		String input = str;
		//quit if: data is null or is an empty string
		if(input == null || input.equals(new String()) || input == "") return new String[0];

		//create a buffer array for the worst case scenario of data size
		Vector dataBuffer = new Vector();
		int index = 0;
		
		//cut the string begining at the given index if needed
		if(initIndex > 0)
			input = input.substring(initIndex);

		//take the current string, split it into two strings: the next piece of data (before delimiter)
		//and the rest of the input string (after the delimiter). Set the input to the
		//later of the two strings and repeat. 
		//Break IF: input is null, we have reached the end of all the data we want 
		while(true)
		{
			if(input == null) break;
			int cutIndex = input.indexOf(delimiter);
			if(cutIndex == -1)
			{
				if(withEnd)
					dataBuffer.addElement(input);
				break;
			}
			String sub1 = input.substring(0,cutIndex);
			String sub2 = input.substring(cutIndex+1);

			dataBuffer.addElement(sub1);
			input = sub2;
		}	
		//convert the buffer to an array and return it
		String[] d = new String[dataBuffer.size()];
	
		int l = dataBuffer.size();
		for(int i=0;i<l;i++)
		{
			d[i]=(String)dataBuffer.elementAt(i);
		}
		return d;
	}

	/**
 	 * Convert a multi line string to a single line string
 	 *
 	 * @param input string to convert
 	 * @return single line string
 	 */
	public static String noNewLine(String input)
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
//==============================FOR TESTING THIS CLASS==========================================|
/*
	public static void main(String[] args)
	{
		String testString = "Data1,Data2,,,";
		String[] values = rawSplit(testString,",");
		for(int i=0;i<values.length;i++)
		{
			if(values[i].equals("")){ System.out.println("null"); continue; }
			System.out.println(values[i]);
		}
	}
*/
}
