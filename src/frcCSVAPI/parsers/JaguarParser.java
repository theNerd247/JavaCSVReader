 /*
 * Copyright (c) 2012 theNerd247 (Noah Harvey)
 *
 * This product is licensed under the BSD 2-clause license
 * See LICENSE.txt for more details
 */

package frcCSVAPI.parsers;

import frcCSVAPI.RobotDevice;
import java.util.Vector;
import edu.wpi.first.wpilibj.Jaguar;

/**
 *
 * @author theNerd247 (Noah Harvey)
 */
public class JaguarParser extends DeviceParser
{
	private  String name = "JaguarParser";

	public  String getName()
	{
		return name;
	}

	public  RobotDevice createDevice(Vector data)
	{
		String name = (String)data.elementAt(0);
		int slot = ((Integer)data.elementAt(2)).intValue();
		int channel = ((Integer)data.elementAt(3)).intValue();
		Jaguar newDev = new Jaguar(slot,channel);
		return new RobotDevice(name,newDev);
	}
}	
