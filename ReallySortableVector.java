
/**
 * @author your_name
 * This class represent a vector of location with 3 sorting algorithms
 */

import java.util.ArrayList;
import java.util.Arrays;

public class ReallySortableVector {
    private ArrayList<Location> thePlaces;


    //return the distance between two locations p1 and p2
    private double distance(Location p1, Location p2)
    {
        int x1 = p1.getX();
        int y1 = p1.getY();
        int x2 = p2.getX();
        int y2 = p2.getY();

        int dx = x1 - x2;
        int dy = y1 -y2;

        return Math.sqrt( dx*dx + dy *dy );
    }
    //construct the ReallySortableVector with empty array
    public ReallySortableVector()
    {
        thePlaces = new ArrayList<Location>();
    }

    //add location to this vector
    public void addLocation(Location location)
    {
        thePlaces.add(location);
    }


    //sort the items in array thePlaces
    //into alphabetical order by their name using bubble sort
    public void sortBubble()
    {
        //copy content of thePlaces to array
        Location[] a = new  Location[thePlaces.size()];
        thePlaces.toArray(a);

        for( int i= a.length-1; i>1; i--)
        {
            for( int j = 0; j < i; j++)
            {
                if ( a[j].getName().compareTo( a[j+1].getName() ) > 0 )
                {
                    //swap a[j] and a[j+1]
                    Location temp = a[j+1];
                    a[j+1] = a[j];
                    a[j] = temp;
                }
            }

        }
        thePlaces.clear();
        //add array a to thePlaces
        thePlaces.addAll(Arrays.asList(a));
    }

    //sort by the x coordinate ascending
    private void sortQuickRecursive(Location[] a, int low, int high)
    {
        if ( low >= high ) return;
        int mid = a[ (low+high)/2 ].getX();
        int i = low;
        int j = high;
        while( i < j)
        {
            while ( i <= j && a[i].getX() < mid ) i++;
            while ( i <= j && a[j].getX() > mid ) j--;
            if ( i <= j)
            {
                //swap a[i], a[j]
                Location temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                i++;
                j--;
            }
        }

        sortQuickRecursive( a, low, j);
        sortQuickRecursive( a, i, high );
    }
    //sort the items in array thePlaces
    //by the x coordinate ascending using quick sort
    public void sortQuick()
    {
        //copy content of thePlaces to array
        Location[] a = new  Location[thePlaces.size()];
        thePlaces.toArray(a);

        sortQuickRecursive(a, 0, a.length -1 );

        thePlaces.clear();
        //add array a to thePlaces
        thePlaces.addAll(Arrays.asList(a));
    }

    //sort by the y coordinate ascending using radix sort
    private static void radixSortRecursive(int bitN, Location[] a,
                                            Location[] temp, int low, int high)
    {
        if ( bitN < 0 ) return;
        if ( low >= high ) return;
        //position from low to p1 ( exclusive) is store number with bitN is 0
        int p1 = low;

        //position from low to p2 ( exclusive) is store number with bitN is 0
        int p2 = high;

        for (int i= low; i<= high; i++)
        {
            //get bit N of Y coordinate
            if ( (a[i].getY() & ( 1 << bitN )) == 0 )
            {
                temp[p1++] = a[i];
            }
            else
            {
                temp[p2--] = a[i];
            }
        }

        //copy temp back to a
        for (int i= low; i<= high; i++)
        {
            a[i] = temp[i];
        }
        radixSortRecursive( bitN-1, a, temp, low, p1-1);
        radixSortRecursive( bitN-1, a, temp, p2+1, high);
    }

    //sort the items in array thePlaces
    //by the y coordinate ascending using radix sort
    //this is binary radix sort, and we assume coordinate y >= 0 and has 32 bits
    //since the most significant bit is 0 ( y >= 0 ), we only sort from 30th bit
    //note: the least significant bit is the 0th bit
    public void sortRadix()
    {
        //copy content of thePlaces to array
        Location[] a = new  Location[thePlaces.size()];
        thePlaces.toArray(a);

        Location[] temp = new Location[a.length];
        radixSortRecursive(30, a, temp,0, a.length-1);

        thePlaces.clear();
        //add array a to thePlaces
        thePlaces.addAll(Arrays.asList(a));
    }

    // return the total distance of following a straight line path between the
    //items in the order they appear in the ArrayList

    public double findDistance()
    {
        if ( thePlaces.size() <= 1 ) return 0;

        double d = 0;
        Location p1 = thePlaces.get(0);
        for (int i=1; i< thePlaces.size(); i++)
        {
            Location p2 = thePlaces.get(i);
            d += distance( p1, p2);
            p1 = p2;
        }
        return d;
    }

    //print the content of vector
    public void print()
    {
        for (int i=0; i< thePlaces.size(); i++)
        {
            System.out.println( thePlaces.get(i) );
        }
    }
}
