
/** A two dimensional vector class.
 *  This class has a single dimensional vector of Vector objects.
 *  It then has translation functions that translate coordinates
 *  to and from a 2D coordinate system.
 *  @author Ben Davenport-Ray
 */
public class Vector2D
{

    private Vector items;

    int width;
    int length;

    public Vector2D()
	{
        
    }

    public Vector2D(String[] data, int width, int length)
	{ 
    }

    public void addRow(){
                
    }

    public void addColumn(){
        
    }

    public Object getItem(int row, int column){
        return items.get(row).get(column);
    }

    public void setItem(int row, int column, Object item)[
        items.get(row).set(column, item);
    }

    public void toString()
	{
        //Woe be to any programmer who wants to print a 2D vector....
        
        String dump;
        for (int i = 0; i < items.len; i++){
            dump = items[i].toString() + "\n";
        }    
        return dump;
    }
}
