 /*
 * Copyright (c) 2012 theNerd247 (Noah Harvey)
 *
 * This product is licensed under the BSD 2-clause license
 * See LICENSE.txt for more details
 */

package frcCSVAPI;

/**
 * An adapter class that contains a robot device
 * @author theNerd247 (Noah Harvey)
 */
public class RobotDevice
{
	private String name;
	private Object device;

	/**
  	 * Creates a new RobotDevice object 
  	 *
  	 * @param name the name of the device (e.g: FrontLeftJaguar)
  	 * @param device the device to contain
  	 */  
	public RobotDevice(String name, Object device)
	{
		this.name = name;
		this.device = device;
	}
	
	/** @param dev the new device to contain */ 
	public void setDevice(Object dev){device=dev;}

	/** @return the contained device */
	public Object getDevice(){return device;}
	
	/** @param newName the new name for the device */
	public void setName(String newName){name=newName;}

	/** @return the name of the device */
	public String getName(){return name;}
}	
