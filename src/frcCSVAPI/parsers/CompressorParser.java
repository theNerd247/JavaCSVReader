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
		int switchSlot = Integer.parseInt((String)data.elementAt(2));
		int switchChannel = Integer.parseInt((String)data.elementAt(3));
		int relaySlot = Integer.parseInt((String)data.elementAt(4));
		int relayChannel = Integer.parseInt((String)data.elementAt(5));
		Compressor newDev = new Compressor(switchSlot,switchChannel
								,relaySlot,relayChannel);
		return new RobotDevice(name,newDev);
	}
}	
