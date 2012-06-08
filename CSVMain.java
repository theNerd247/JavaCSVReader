import java.util.Vector;
public class CSVMain 
{
	public static void main(String[] args)
	{
		CSVFile file = new CSVFile("/home/boys/Documents/Noah/src/GIT/JavaCSVReader/test.txt");//"C:\\Users\\Developer\\Desktop\\GIT\\JavaCSVReader\\test.txt");

		Vector dta = file.getData();
		CSVDataHeader header = (CSVDataHeader)dta.elementAt(0);
		Vector data = header.getData();
		Vector intData = (Vector)(data.elementAt(0));
		//Integer integer= (Integer)(intData.elementAt(1));
		String[] name = header.getHeader();
		for(String i : name)
			System.out.print(i+",");
		
		System.out.println("\nend");
		
	}
}	
