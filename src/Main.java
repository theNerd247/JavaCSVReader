/*
 * Copyright (c) 2012 theNerd247 (Noah Harvey)
 *
 * This product is licensed under the BSD 2-clause license
 * See LICENSE.txt for more details
 */

import JavaCSVReader.*;
/**
 *
 * @author theNerd247 (Noah Harvey)
 */
public class Main
{
	public static void main(String[] args)
	{
		CSVFile file = new CSVFile("test");
		System.out.println(file.getHeaders().size());
		String[] names = {"A","B","C"};
		CSVDataHeader header = new CSVDataHeader("jo",names);
			header.appendToColumn("a",0);
			header.appendToColumn("b",0);
			header.appendToColumn("c",0);
			header.autosize(3,3);
		file.addHeader(header);
		file.save();
	}	
}	
