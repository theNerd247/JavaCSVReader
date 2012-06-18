
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
		return (instance != null) ? instance : instance = new RobotDevices(); 
	}

	protected RobotDevices()
	{
		//add stuff later
	}
	//---------------------------------------------|
}	
