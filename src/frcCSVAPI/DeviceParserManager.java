 /*
 * Copyright (c) 2012 theNerd247 (Noah Harvey)
 *
 * This product is licensed under the BSD 2-clause license
 * See LICENSE.txt for more details
 */

package frcCSVAPI;

import JavaCSVReader.StringUtils;
import java.util.Hashtable;
import frcCSVAPI.parsers.DeviceParser;

/**
 * This class acts as a container for robot device
 * parsers 
 * @author theNerd247 (Noah Harvey)
 */
public class DeviceParserManager
{
	/** the container of parsers*/
	private static Hashtable parsers = new Hashtable();

	/**
 	 * Add a parser to the list
 	 *
 	 * @param newParser the new parser to add
 	 */ 	
	public static void addParser(DeviceParser newParser)
	{
		parsers.put(newParser.getName(),newParser);
	}

	/**
 	 * Removes a parser from the list 
 	 *
 	 * @param parserName the name of the parser to remove
 	 */ 
	public static void removeParser(String parserName)
	{
		parsers.remove(parserName);
	}

	/**
 	 * Returns a DeviceParser
 	 *
 	 * @param parserName the name of the parser to return
 	 */ 
	public static DeviceParser getParser(String parserName)
	{
		return (DeviceParser)parsers.get(parserName);
	}
}	
