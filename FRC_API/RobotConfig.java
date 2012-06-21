package JavaCSVReader.FRC_API;

import JavaCSVReader.CSVFile;
/*
 * This class is the main interface for FRC robots
 * and the JavaCSVReader lib. It acts as a config manager
 * for robot devices and properties
 *@author theNerd247 (Noah Harvey)
 */
public class RobotConfig
{
	//file that contains all data
	CSVFile configFile = null;
	
	//-------------SINGLETON---------------------|
	private static RobotConfig instance = null;
	private static String path = "";
	public static void init(String configFilePath)
	{
		path = configFilePath;
	
	}
	public static RobotConfig getInstance()
	{
		if(instance == null) instance = new RobotConfig();
		return instance;
	}

	protected RobotConfig()
	{
		configFile = new CSVFile(path);
	}
	//---------------------------------------------|

	public static Object getDevice(String deviceName)
	{
		return new Object();//later
	}

	public static Object getProperty(String propertyName)
	{
		return new Object();//later
	}

	public static void addDevice(Object device)
	{
		;//later
	}
}
