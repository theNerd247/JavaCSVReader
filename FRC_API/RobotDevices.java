package JavaCSVReader.FRC_API;

import JavaCSVReader.CSVFile;
/*
 * This class acts as a container for robot
 * devices. 
 *@author theNerd247 (Noah Harvey)
 */
public class RobotDevices
{
	//-------------SINGLETON---------------------|
	private static RobotDevices instance = null;
	
	public static RobotDevices getInstance()
	{
		if(instance == null) instance = new RobotDevices();
		return instance;
	}

	protected RobotDevices()
	{
		//add stuff later
	}
	//---------------------------------------------|
}	
