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



public class Battleship
{
    private int[][] board = new int[10][10];
    private String[][] guesses = new String[10][10];
    //private int hitcounter = 0;
    //private int[] locationCells;
    private int row;
    private int column;
    private boolean horizontal;
    private int[] numOfHits = {0, 0, 0, 0, 0};
    private int sunkenShips = 0;

    public Battleship()
    {
        int numOfShips = 0;
        int num = 0;
        while (numOfShips < 5) // creates 5 unique ships
        {
            int lenRow = board.length; // length of the row is 10
            int maxShip = 6; // value is 9
            int shipSize = 5;

            boolean horizontal = false;
            int hor = (int) (Math.random() * 2);

            int[] locs = new int[5];

            //System.out.println("\n hor is " + hor);
            if (hor == 1) // sets up rows/columns for horizontal ship
            {
                horizontal = true;
                setHor(horizontal);

                int row = (int) (Math.random() * lenRow); // it can be in any of the 10 rows
                setRow(row);
                int column = (int) (Math.random() * maxShip); // generates a number between 0 and 5
                setCol(column);

                //System.out.println("\nhorizontal");
            }
            else if (hor == 0)// sets up rows/columns for vertical ship
            {
                horizontal = false;
                setHor(horizontal);

                int row = (int) (Math.random() * maxShip); // generates a number between 0 and 5
                setRow(row);
                int column = (int) (Math.random() * lenRow); // it can be in any of the 10 rows
                setCol(column);

                //System.out.println("\nvertical");
            }
            //System.out.println("ANSWERS : column is " + column);
            //System.out.print("ANSWERS : row is " + row + "\n");
            boolean clear = false;
            //System.out.println("ANSWERS : row is " + row);
            if(board[row][column] == 0) // check if the starting point is equal to 0 aka not on top of a ship
            {
                if (horizontal)
                {
                    for (int k = 1; k < shipSize; k++) //ship size (currently 5)
                    {
                        if (board[row][column+k] == 0) // checks if the following positions are not on top of a ship
                        {
                            clear = true;
                        }
                        else
                        {
                            clear = false;
                            break;
                        }
                        //System.out.println("row is " + row + "\n");
                        //System.out.println("col is " + (column + k) + "\n");
                    }
                    //System.out.println("BOAT " + (num + 1) + " is "); 
                    //System.out.println("ANSWERS : column is " + column);
                    //System.out.print("ANSWERS : row is " + row + "\n");
                }
                else 
                {
                    for (int k = 1; k < shipSize; k++) //ship size (currently 5)
                    {
                        if (board[row+k][column] == 0)// checks if the following positions are not on top of a ship
                        {
                            clear = true;
                        }
                        else 
                        {
                            clear = false;
                            break;
                        }
                        //System.out.println("row is " + (row + k) + "\n");
                        //System.out.println("col is " + column + "\n");
                    }
                    //System.out.println("BOAT " + (num + 1) + " is "); 
                    //System.out.println("ANSWERS : column is " + column);
                    //System.out.print("ANSWERS : row is " + row + "\n");
                }
            }
            if (clear) // if not overlapped, then will place down the boat marked by the number of the boat
            {
                if (horizontal)
                {
                    for (int p = 0; p <  shipSize; p++)
                    {
                        board[row][column+p] = 1 + num;
                    }
                }
                else
                {
                    for (int p = 0; p <  shipSize; p++)
                    {
                        board[row+p][column] = 1 + num;
                    }
                }
                num++;
                numOfShips++;
            }

            //System.out.print("ANSWERS : " + column + " " + (column + 1) + " " + (column + 2) + "\n");
            //System.out.print("ANSWERS : " + row + " " + (row + 1) + " " + (row + 2) + "\n");
            //locationCells = locs;

            for (int s = 0; s < 10; s++) // initializes the board of recorded guesses that shows hits and misses
            {
                for (int t = 0; t < 10; t++)
                {
                    guesses[s][t] = " ";
                }
            }
        }
    }
    
    //     public void printAnswer() //IF YOU WANT TO SEE ANSWER BOARD, THEN UNCOMMENT (prints the board and shows where the ships are)
    //     {
    //         // PRINTING ANSWERS BELOW, SEE WARM UP FROM OCT 1 << my reference for how to do a loop inside a loop
    //         //int[] locs = getLocationCells();
    // 
    //         //if (horizontal)
    //         //{
    //         //    for (int w = 0; w < 5; w++)
    //         //    {
    //         //        board[row][locs[w]] = 1;
    //         //    }
    //         //}
    //         //else 
    //         //{
    //         //    for (int w = 0; w < 5; w++)
    //         //    {
    //         //        board[locs[w]][column] = 1;
    //         //    }
    //         //}
    //         System.out.println("  0 1 2 3 4 5 6 7 8 9");
    //         for (int rowPrint = 0; rowPrint < 10; rowPrint++)
    //         {
    //             System.out.print(rowPrint + " ");
    //             for (int colPrint = 0; colPrint < 10; colPrint++)
    //             {
    //                 System.out.print(board[rowPrint][colPrint] + " ");
    //             }
    //             System.out.println("");
    //         }
    //     }

    public void printGuesses() // prints the players guesses, "-" is miss, "X" is hit
    {
        System.out.println("  0 1 2 3 4 5 6 7 8 9");
        for (int rowPrint = 0; rowPrint < 10; rowPrint++)
        {
            System.out.print(rowPrint + " ");
            for (int colPrint = 0; colPrint < 10; colPrint++)
            {
                System.out.print(guesses[rowPrint][colPrint] + " ");
                //System.out.println("
            }
            System.out.println("");
        }
    }

    //public int[] getLocationCells()
    //{
    //    return locationCells;
    //}

    private void setHor(boolean hor)
    {
        horizontal = hor;
    }

    private void setRow(int rowNum)
    {
        row = rowNum;
    }

    private void setCol(int colNum)
    {
        column = colNum;
    }

    public void check(int rowG, int colG) //
    {
        //System.out.println("hitcounter before is " + hitcounter);
        //System.out.print(locationCells[0]);
        //System.out.print(locationCells[1]);
        //
        //System.out.print(locationCells[2]);
        //System.out.println("Enter a number, 0 through 4. ");
        //int guess = kbReader.nextInt();
        //                      if (guess == locationCells[0] || guess == locationCells[1] || guess == locationCells[2])
        int rowGuess = rowG; 
        int colGuess = colG;
        int boardlength = board.length-1;
        if (colGuess > boardlength || rowGuess > boardlength) // if guess is out of bounds of the game board
        {
            System.out.println("out of bounds, guess again");
        }
        else if (board[rowGuess][colGuess] == 9) // if already guessed, guesses are ALWAYS changed to 9 by default (see bottom of method)
        {
            System.out.println("you already guessed this, guess again");
        }
        else if (board[rowGuess][colGuess] != 0) // if the guess is not on an empty cell (anything that's value is 1-5 is a hit)
        {
            //hitcounter++;

            System.out.println("Hit!");
            guesses[rowGuess][colGuess] = "X";

            int boatValue = board[rowGuess][colGuess]; // get the boat number that was hit (will be a number 1-5)
            numOfHits[boatValue - 1]++; // increments the hit counter for that specific boat

            if (numOfHits[boatValue-1] == 5) // if the hit counter is equal to 5, that means the ship has sunken
            {
                System.out.println("Sank a ship!");
                //System.out.println("sank ship number " + boatValue);
                sunkenShips++;
                //System.out.println("num of sunken ships is now " + sunkenShips);
            }

            setSunkenShips(sunkenShips);
            //setHitcounter(hitcounter); // resets hitcounter after it increments
            //System.out.println("hitcounter after is " + hitcounter);
        }
        else // landed on a cell who's value is 0/empty cell
        {
            System.out.println("Miss!");
            guesses[rowGuess][colGuess] = "-";
        }
        board[rowGuess][colGuess] = 9; // changes by defaut to record the guess to tell the user if they have already guessed it (see above)
    }

    public void setSunkenShips(int num)
    {
        sunkenShips = num;
    }

    public int getSunkenShips()
    {
        return sunkenShips;
    }

    //public int getHitcounter()
    //{
    //    return hitcounter;
    //}

    //private void setHitcounter(int num)
    //{
    //    hitcounter = num;
    //}
}