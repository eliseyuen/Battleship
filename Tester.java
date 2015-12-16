// By default, this game is a 2D array, 10 by 10, that has 5 randomly generated battle ships, each length 3, horizontally or vertically.
// Ships are not allowed to overlap or run off the grid.
import java.util.Scanner;
public class Tester
{
    public static void main(String[] args)
    {
        Scanner kbReader = new Scanner(System.in);
        Battleship test = new Battleship();
        
        //test.printAnswer();
        
        int guesses = 0;
        //int hitcounter = test.getHitcounter();
        
        int sunkenShips = test.getSunkenShips();
        //int[] locations = test.getLocationCells();
        
        test.printGuesses();
        
        //System.out.println("\nlocations.length is " + locations.length);
        while (sunkenShips < 5) 
        {
            System.out.println("Enter your row guess. ");
            int rowGuess = kbReader.nextInt();
            System.out.println("Enter your column guess. ");
            int colGuess = kbReader.nextInt();
            //System.out.println("hitcounter is " + hitcounter);

            test.check(rowGuess, colGuess);
            //System.out.println("MAIN hitcounter is " + hitcounter);
            //System.out.println("locations.length is " + locations.length);
            //hitcounter = test.getHitcounter();
            
            test.printGuesses();
            
            sunkenShips = test.getSunkenShips();
            
            //System.out.println("MAIN sunken ships is " + sunkenShips);
            
            guesses++;
            if (sunkenShips == 5)
            {
                System.out.println("You win!");
                System.out.println("You took " + guesses + " guesses.");
                break;
            }
        }
    }
}