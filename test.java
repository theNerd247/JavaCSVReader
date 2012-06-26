/*
 * Copyright (c) 2012 theNerd247 (Noah Harvey)
 *
 * This product is licensed under the BSD 2-clause license
 * See LICENSE.txt for more details
 */
import java.util.Vector;

/**
 *
 *@author theNerd247 (Noah Harvey)
 */
public class test
{
	public static void main(String[] args)
	{	
		Vector v = new Vector();
		int i=0;
		while(i<15)	
		{
			v.add(null);
			i++;
		}
		System.out.println("SIZE: "+v.size());
		System.out.println("CAPA: "+v.capacity());
		System.out.println(v.isEmpty());
	}	
}	
