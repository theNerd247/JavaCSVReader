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
		int aSlot = Integer.parseInt((String)data.elementAt(2));
		int aChannel = Integer.parseInt((String)data.elementAt(3));
		int bSlot = Integer.parseInt((String)data.elementAt(4));
		int bChannel = Integer.parseInt((String)data.elementAt(5));
		boolean direction = ((Boolean)data.elementAt(6)).booleanValue();
		Encoder newDev = new Encoder(aSlot,aChannel,bSlot,bChannel,direction);
		return new RobotDevice(name,newDev);
	}
}	
