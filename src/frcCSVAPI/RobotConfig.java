 /*
 * Copyright (c) 2012 theNerd247 (Noah Harvey)
 *
 * This product is licensed under the BSD 2-clause license
 * See LICENSE.txt for more details
 */

package frcCSVAPI;

import com.sun.squawk.microedition.io.FileConnection;
import frcCSVAPI.parsers.*;
import JavaCSVReader.CSVFile;
import JavaCSVReader.FileIO;
import JavaCSVReader.CSVDataHeader;
import javax.microedition.io.Connector;
import java.util.Hashtable;
import java.util.Vector;

/**
 * This class is the intercacing class 
 * to the robot devices and their config state
 * <p>
 * This class reads and writes to the given JavaCSVReader file
 * that is located on the CRio. Each device is stored with a
 * name that is used to identify it.
 *
 * @see JavaCSVReader.CSVFile
 * @see JavaCSVReader.CSVDataHeader
 * @author theNerd247 (Noah Harvey)
 */
public class RobotConfig extends CSVFile
{
	Hashtable devices = new Hashtable();
	/**
 	 * Initializes a new RobotConfig class by 
 	 * opening and reading the file defined by the given path. 
 	 * If the file does not exist it will be created to when 
 	 * calling the save method. 
 	 *
 	 * @param path the path of the file to read/write data from
 	 * @see JavaCSVReader.CSVFile#save()
 	 */ 
	public RobotConfig(String path)
	{
		super(path);
		initParsers();
		createDevices();		
	}


	/** Overrides {@link JavaCSVReader.CSVFile#readFileData()} */ 
	protected String readFileData()
	{
		if(path.equals("") || path == null) return null;
		String data = new String();
		try
		{
			FileConnection fl = (FileConnection)Connector.open(path,Connector.READ);
			
			name = fl.getName();
			FileIO.readFileData(fl.openInputStream(), encoding);
		}
		catch(Exception e)
		{
			//possibly create a logging class to cache error messages
			System.out.println(e.getMessage()); 
		}
		return data;
	}

	/** Overriedes {@link JavaCSVReader.CSVFile#writeFileData()}*/ 
	protected void writeFileData(String data)
	{
		try
		{
			FileConnection fl = (FileConnection)Connector.open(path,Connector.WRITE);
			
			name = fl.getName();
			//open the file to write (overwrite any data in the file)
			FileIO.writeFileData(data,fl.openOutputStream(),encoding);
		}
		catch(Exception e)
		{
			return;
		}
	}

	/**
 	 * Initializes default parsers for devices
 	 */ 
	private void initParsers()
	{
		DeviceParserManager.addParser(new AccelerometerParser());
		DeviceParserManager.addParser(new CompressorParser());
		DeviceParserManager.addParser(new EncoderParser());
		DeviceParserManager.addParser(new EncoderParser());
		DeviceParserManager.addParser(new GyroParser());
		DeviceParserManager.addParser(new JaguarParser());
		DeviceParserManager.addParser(new RelayParser());
		DeviceParserManager.addParser(new ServoParser());
		DeviceParserManager.addParser(new SolenoidParser());
	}

	/**
 	 * Creates the robot devices based on the headers contained.
 	 * <p>
 	 * See FRC-FORMAT.txt for formatting the headers
 	 * <b>If no parser is found (given in the 2nd item of a CSVDataHeader row)
 	 * then no device is created</b>
 	 */
	private void createDevices()
	{
		CSVDataHeader header = getHeader("Devices");
		//iterates through all the rows in the data-header. 
		//and creates a new device based on the parser type given
		//within the data. If the parser is not found then no device 
		//is created
		for(int i=0;i<header.getTableHeight();i++)
		{
			Vector row = header.getRow(i);
			String parserName = ((String)row.elementAt(1));
			DeviceParser parser = DeviceParserManager.getParser(parserName);
			if(parser == null) continue;
			RobotDevice device = parser.createDevice(row);
			devices.put(device.getName(),device);
		}
	}

	/**
 	 * Returns a device based on the given name for that device.
 	 * Simply cast the return value to get the class needed. 
 	 * 
 	 * @param name the name of the device
 	 * @return the object representation of that device (cast to 
 	 * proper value). Returns <code>null</code> if name is invalid
 	 */ 
	public Object getDevice(String name)
	{
		return ((RobotDevice)devices.get(name)).getDevice();
	}

	/**
 	 * Sets the device to the given Object. If the 
 	 * device name did not exist previously then
 	 * a new device is created insntead. 
 	 *
 	 * @param name the name of the device to set
 	 * @param device the new device
 	 */ 
	public void setDevice(String name, Object device)
	{
		RobotDevice newDev = new RobotDevice(name,device);
		devices.put(newDev.getName(),newDev);
	}

}	
