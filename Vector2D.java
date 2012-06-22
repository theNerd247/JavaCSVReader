import java.util.Vector;

//class to implement table style container using vectors
public class Vector2D
{
	private Vector items;
	private int width;
	private int height;
	
	public Vector2D(){this(10,10);}

	public Vector2D(int width, int height)
	{
		this.width = width;
		this.height = height;
		//initialize the containing vector
		//and the column containers to 
		//have an increment size to that of 2
		items = new Vector(width,2);
		for(int i=0;i<width;i++)
			items.add(new Vector(height,2));
	}

	//append a new vector of objects to the end of the 
	//columns
	public void appendColumn(Vector newColumn)
	{
		items.addElement(newColumn);
		width++;
	}

	//append a new row of objects to the end of the
	//rows
	public void appendRow(Vector newRow)
	{
		for(int i=0;i<items.size();i++)
		{
			Vector col = (Vector)items.elementAt(i);
			col.addElement(newRow.elementAt(i));
		}
		height++;
	}

	public void insertItemAt(Object obj, int x, int y)
	{
		((Vector)items.elementAt(x)).insertElementAt(obj,y);
	}

	public void appendToColumn(Object obj, int x)
	{
		((Vector)items.elementAt(x)).add(obj);
	}

	public void removeItemAt(int x , int y)
	{
		((Vector)items.elementAt(x)).removeElementAt(y);
	}
	
	public Object getElementAt(int x, int y)
	{
		return ((Vector)items.elementAt(x)).elementAt(y);
	}

	public Vector getItems()
	{
		return items;
	}
	
	
}	
