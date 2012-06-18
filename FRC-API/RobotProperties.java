
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
		return (instance != null) ? instance : instance = new RobotProperties(); 
	}

	protected RobotProperties()
	{
		//add stuff later
	}
	//---------------------------------------------|
}	
