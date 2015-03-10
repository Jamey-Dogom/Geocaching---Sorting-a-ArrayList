/**
 * @author your_name
 * This class is the main function
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Driver4 {

    private static final String INPUT_FILE= "locations.txt";

    // read the contents of file locations.txt
    //throws Exception if error when redaing file
    public static void readFile(ReallySortableVector table) throws Exception
    {

       Scanner input = new Scanner( new File(INPUT_FILE));
       while( input.hasNextLine())
       {
           String line = input.nextLine().trim();
           if ( !line.equals("") )
           {
               //parse the line to create location
               Scanner parser = new Scanner(line);
               int x = parser.nextInt();
               int y = parser.nextInt();
               String name = parser.nextLine().trim();
               Location location = new Location(name, x,y);
               table.addLocation(location);
           }
       }
       input.close();
    }

    // main method, thrwo Exception if error occurrs
    public static void main(String[] args) throws Exception {
        ReallySortableVector table = new ReallySortableVector();
        System.out.println("Welcome to Project 4.\n");
        System.out.println("Reading data from " + INPUT_FILE);
        readFile(table);

        //print Ann's Path
        table.sortBubble(); //sort by name
        System.out.println("Ann's Path");
        table.print();
        System.out.println( table.findDistance() + " miles");

        //print Bob's Path
        table.sortRadix(); //sort by name
        System.out.println("\nBob's Path");
        table.print();
        System.out.println( table.findDistance() + " miles");

        //print Carlos's Path
        table.sortQuick(); //sort by x coordinate
        System.out.println("\nCarlos's Path");
        table.print();
        System.out.println( table.findDistance() + " miles");


    }
}
