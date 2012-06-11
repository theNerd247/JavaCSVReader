import java.util.Vector;

//class to extend the utilities of the String class.
//NOTE: this class may contain methods that have nothing to do with the JavaCSVReader lib. 
//    : extra methods are kept for robotics team's usage in their FRC code.
public class StringUtils
{
	//overloads of split
	//split, save extra data defined past last delimiter, start at begining of string
	public static String[] split(String input, String delimiter){return split(input,delimiter,true,0);}
	//as above but start at different index
	public static String[] split(String input, String delimiter, int start){return split(input,delimiter,true,start);}
	//split but DON'T return data past the last delimiter
	public static String[] rawSplit(String input, String delimiter){return split(input,delimiter,false,0);}

	//split data found only in between a given delimiter - similiar to parsing html
	public static String[] tagSplit(String input, String delimiter)
	{
		Vector buffer = new Vector();
		while(true)
		{
			//find the begin index of the first set of encasing delimiters
			int begin = input.indexOf(delimiter);
			int end = input.indexOf(delimiter,begin+1);
			
			if(begin == -1 || end == -1) break; 			

			buffer.add(input.substring(begin+1,end));
			input = input.substring(end+1);
		}
		int size = buffer.size();
		String[] d = new String[size];
		for(int i=0;i<size;i++)
		{
			d[i] = (String)buffer.elementAt(i);
		}
		return d;
	}

	//same as tagsplit, but return the data that is not encased in the given delimiter
	public static String[] invertTagSplit(String input, String delimiter)
	{
		int start = input.indexOf(delimiter);
		return tagSplit(input.substring(start+1),delimiter);
	}
	
	//used to scan through the given text and return all data before or inbetween the given delimiter
	//the last item in the array returned is the rest of the String AFTER the last delimiter if withEnd is true
	public static String[] split(String str, String delimiter,boolean withEnd,int initIndex)
	{
		String input = str;
		//quit if: data is null or is an empty string
		if(input == null || input.equals(new String()) || input == "") return new String[0];

		//create a buffer array for the worst case scenario of data size
		Vector dataBuffer = new Vector();
		int index = 0;
		
		//cut the string begining at the given index if needed
		if(initIndex > 0)
			input = input.substring(initIndex);

		//take the current string, split it into two strings: the next piece of data (before delimiter)
		//and the rest of the input string (after the delimiter). Set the input to the
		//later of the two strings and repeat. 
		//Break IF: input is null, we have reached the end of all the data we want 
		while(true)
		{
			if(input == null) break;
			int cutIndex = input.indexOf(delimiter);
			if(cutIndex == -1)
			{
				if(withEnd)
					dataBuffer.add(input);
				break;
			}
			String sub1 = input.substring(0,cutIndex);
			String sub2 = input.substring(cutIndex+1);

			dataBuffer.add(sub1);
			input = sub2;
		}	
		//convert the buffer to an array and return it
		String[] d = new String[dataBuffer.size()];
	
		int l = dataBuffer.size();
		for(int i=0;i<l;i++)
		{
			d[i]=(String)dataBuffer.elementAt(i);
		}
		return d;
	}

	//take the given string and return it as a string with no carriage returns
	public static String noNewLine(String input)
	{
		String[] choppedString = split(input,"\n");
		String s = new String();
		int l = choppedString.length;

		for(int i=0;i<l;i++)
		{
			s=s+choppedString[i];
		}

		return s;
	}
//==============================FOR TESTING THIS CLASS==========================================|
	public static void main(String[] args)
	{
		String testString = "Data1,Data2,,,";
		String[] values = rawSplit(testString,",");
		for(int i=0;i<values.length;i++)
		{
			if(values[i].equals("")){ System.out.println("null"); continue; }
			System.out.println(values[i]);
		}
	}
}
