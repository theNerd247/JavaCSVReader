 /*
 * Copyright (c) 2012 theNerd247 (Noah Harvey)
 *
 * This product is licensed under the BSD 2-clause license
 * See LICENSE.txt for more details
 */

package frcCSVAPI.parsers;

import frcCSVAPI.RobotDevice;
import edu.wpi.first.wpilibj.Encoder;
import java.util.Vector;

/**
 *
 * @author theNerd247 (Noah Harvey)
 */
public class EncoderParser extends DeviceParser
{
	private  String name = "EncoderParser";

	public  String getName()
	{
		return name;
	}

	public  RobotDevice createDevice(Vector data)
	{
		String name = (String)data.elementAt(0);
		int aSlot = ((Integer)data.elementAt(2)).intValue();
		int aChannel = ((Integer)data.elementAt(3)).intValue();
		int bSlot = ((Integer)data.elementAt(4)).intValue();
		int bChannel = ((Integer)data.elementAt(5)).intValue();
		boolean direction = ((Boolean)data.elementAt(6)).booleanValue();
		Encoder newDev = new Encoder(aSlot,aChannel,bSlot,bChannel,direction);
		return new RobotDevice(name,newDev);
	}
}	
