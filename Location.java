/**
 * @author your_name
 * This class represent a location with name and coordinate
 */
public class Location {
    private String name;
    private int x;
    private int y;

    //construct the location with name and coordinate
    public Location(String _name, int _x, int _y)
    {
        name =_name;
        x = _x;
        y = _y;
    }

    //get the x coordinate
    public int getX()
    {
        return x;
    }

    //get the y coordinate
    public int getY()
    {
        return y;
    }

    //get the name
    public String getName()
    {
        return name;
    }

    //use to print the location
    @Override
    public String toString()
    {
        return x + " " + y + " " + name;
    }
}
