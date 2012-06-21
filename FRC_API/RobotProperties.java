package JavaCSVReader.FRC_API;

import JavaCSVReader.*;
/*
 * Similiar to the RobotProperties class
 * this class acts as a container for RobotProperties
 *@author theNerd247 (Noah Harvey)
 */
public class RobotProperties
{
	//-------------SINGLETON---------------------|
	private static RobotProperties instance = null;
	
	public static RobotProperties getInstance()
	{
		if(instance == null) instance = new RobotProperties();
		return instance;
	}

	protected RobotProperties()
	{
		//add stuff later
	}
	//---------------------------------------------|
}	
