 /*
 * Copyright (c) 2012 theNerd247 (Noah Harvey)
 *
 * This product is licensed under the BSD 2-clause license
 * See LICENSE.txt for more details
 */

package frcCSVAPI.parsers;

import frcCSVAPI.RobotDevice;
import edu.wpi.first.wpilibj.Compressor;
import java.util.Vector;

/**
 *
 * @author theNerd247 (Noah Harvey)
 */
public class CompressorParser extends DeviceParser
{
	private  String name = "CompressorParser";

	public  String getName()
	{
		return name;
	}

	public  RobotDevice createDevice(Vector data)
	{
		String name = (String)data.elementAt(0);
		int switchSlot = ((Integer)data.elementAt(2)).intValue();
		int switchChannel = ((Integer)data.elementAt(3)).intValue();
		int relaySlot = ((Integer)data.elementAt(4)).intValue();
		int relayChannel = ((Integer)data.elementAt(5)).intValue();
		Compressor newDev = new Compressor(switchSlot,switchChannel
								,relaySlot,relayChannel);
		return new RobotDevice(name,newDev);
	}
}	
