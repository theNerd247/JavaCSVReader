public class CSVMain 
{
	public static void main(String[] args)
	{
		CSVFile file = new CSVFile("C:\\Users\\Developer\\Desktop\\GIT\\JavaCSVReader\\test.txt");
		
		file.readFileData();
		file.setMainHeader();
		String[] h = file.getHeader();
		System.out.println(h[0]+" "+h[1]+" "+h[2]);
		String[][] data = CSVParser.parseHeader(file.getRawData(),h[0],h[2]);

		for(String[] i : data)
		{
			System.out.println();
			for(String d : i)
				System.out.print(d+",");
		}
		System.out.println("\nend");
	}
}	
