public class save
{
    private int[][] board = new int[10][10];
    private String[][] guesses = new String[10][10];
    private int hitcounter = 0;
    private int[] locationCells;
    private int row;
    private int column;
    private boolean horizontal;

    public save()
    {
        int lenRow = board.length; // length of the row is 10
        int maxShip = 6; // value is 9
        int shipSize = 5;

        boolean horizontal = false;
        //int hor = (int) (Math.random() * 2);

        int hor = 1;
        
        int[] locs = new int[5];

        //System.out.println("\n hor is " + hor);
        if (hor == 0)
        {
            horizontal = true;
            setHor(horizontal);

            int row = (int) (Math.random() * lenRow); // it can be in any of the 10 rows
            setRow(row);
            int column = (int) (Math.random() * maxShip); // generates a number between 0 and 8
            setCol(column);

            //System.out.println("\nhorizontal");
        }
        else if (hor == 1)
        {
            horizontal = false;
            setHor(horizontal);

            int row = (int) (Math.random() * maxShip); // generates a number between 0 and 8
            setRow(row);
            int column = (int) (Math.random() * lenRow); // it can be in any of the 10 rows
            setCol(column);

            //System.out.println("\nvertical");
        }
        System.out.println("ANSWERS : column is " + column);
        System.out.print("ANSWERS : row is " + row + "\n");

        //System.out.println("ANSWERS : row is " + row);
        if (horizontal)
        {
            for (int k = 0; k < shipSize; k++) //ship size (currently 3)
            {
                board[row][column+k] = 1; // the array value of the random row at the 
                //System.out.println("row is " + row + "\n");
                //System.out.println("col is " + (column + k) + "\n");
            }
            for (int i = 0; i < shipSize; i++) //ship size (currently 3)
            {
                locs[i] = column + i;
            }
        }
        else 
        {
            for (int k = 0; k < shipSize; k++) //ship size (currently 3)
            {
                board[row+k][column] = 1; // the array value of the random row at the
                //System.out.println("row is " + (row + k) + "\n");
                //System.out.println("col is " + column + "\n");
            }
            for (int i = 0; i < shipSize; i++) //ship size (currently 3)
            {
                locs[i] = row + i;
            }
        }
        //System.out.print("ANSWERS : " + column + " " + (column + 1) + " " + (column + 2) + "\n");
        //System.out.print("ANSWERS : " + row + " " + (row + 1) + " " + (row + 2) + "\n");
        locationCells = locs;
        
        for (int s = 0; s < 10; s++)
        {
            for (int t = 0; t < 10; t++)
            {
                guesses[s][t] = " ";
            }
        }
    }

    public void printAnswer() //prints the board and shows where the ship is (notated by a 1)
    {
        // PRINTING ANSWERS BELOW, SEE WARM UP FROM OCT 1
        int[] locs = getLocationCells();

        if (horizontal)
        {
            for (int w = 0; w < 5; w++)
            {
                board[row][locs[w]] = 1;
            }
        }
        else 
        {
            for (int w = 0; w < 5; w++)
            {
                board[locs[w]][column] = 1;
            }
        }
        System.out.println("  0 1 2 3 4 5 6 7 8 9");
        for (int rowPrint = 0; rowPrint < 10; rowPrint++)
        {
            System.out.print(rowPrint + " ");
            for (int colPrint = 0; colPrint < 10; colPrint++)
            {
                System.out.print(board[rowPrint][colPrint] + " ");
            }
            System.out.println("");
        }
    }

    public void printGuesses()
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

    public int[] getLocationCells()
    {
        return locationCells;
    }

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

    public void check(int rowG, int colG)
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
        else if (board[rowGuess][colGuess] == 2)
        {
            System.out.println("you already guessed this, guess again");
        }
        else if (rowGuess == row) // the row is correct, is the column?
        {
            boolean foundOne = false; //resets to false so it won't print "hit" every time
            for(int c = 0; c < locationCells.length; c++)
            {
                //System.out.println("got inside for");
                if (colGuess == locationCells[c]) // found it! row and column are right!
                {
                    //System.out.println("    got inside if");
                    locationCells[c] = -1;
                    hitcounter++;
                    foundOne = true;
                    break;
                }
            }
            //System.out.println("outside the for" + foundOne);

            if (foundOne == true) // if both are right, print hit and change print board
            {
                System.out.println("hit");
                guesses[rowGuess][colGuess] = "X";
                //printAnswer();
                //foundOne = false;
            }
            else // if row is right and column is wrong, print miss
            {
                System.out.println("miss");
                guesses[rowGuess][colGuess] = "-";
                //printAnswer();
            }
            setHitcounter(hitcounter); // resets hitcounter after it increments
            //System.out.println("hitcounter after is " + hitcounter);
        }
        else // row isn't right, so don't bother to check column
        {
            System.out.println("miss");
        }
        board[rowGuess][colGuess] = 2; // originally after the printed hit or miss
    }

    public int getHitcounter()
    {
        return hitcounter;
    }

    private void setHitcounter(int num)
    {
        hitcounter = num;
    }
}