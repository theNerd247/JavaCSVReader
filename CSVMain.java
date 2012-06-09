import java.util.Vector;
public class CSVMain 
{
	public static void main(String[] args)
	{
		CSVFile file = new CSVFile("test.txt");//"C:\\Users\\Developer\\Desktop\\GIT\\JavaCSVReader\\test.txt");

		Vector dta = file.getHeaders();
		
		for(int i=0;i<dta.size();i++)
		{ CSVDataHeader header = (CSVDataHeader)dta.elementAt(i);
			System.out.println(header.getTitle());
			for(String nm : header.getNames())
				System.out.print(nm+",");
			System.out.println();
			Vector d = header.getData();
			for(int c=0;c<d.size();c++)
			{
				Vector j=(Vector)d.elementAt(c);
				for(int k=0;k<j.size();k++)
					System.out.println((String)(j.elementAt(k)));
				System.out.println("====-");
			}
		}
		System.out.println("\nend");
		
	}
}	
