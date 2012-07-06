 /*
 * Copyright (c) 2012 theNerd247 (Noah Harvey)
 *
 * This product is licensed under the BSD 2-clause license
 * See LICENSE.txt for more details
 */
package frcCSVAPI.parsers;

import frcCSVAPI.RobotDevice;
import java.util.Vector;
/**
 * Interface for creating robot parsers
 * @author theNerd247 (Noah Harvey)
 */
public abstract class DeviceParser
{
	/** @return the <code>String</code> name for the parser*/
	public abstract String getName();

	/**
 	 * Returns a new robot device from the given data. 
 	 *<p>
	 * The data is a row of raw String data in Vector format. 
	 * See FRC-FORMAT.txt and README.txt on the formatting and 
	 * parsing of data 
	 *
 	 * @see JavaCSVReader.Vector2D#getRow(int)
 	 * @param rawText the raw file text to parse into a robot device
 	 * @return the new RobotDevice to contain
 	 */ 
	public abstract RobotDevice createDevice(Vector data);
}	
