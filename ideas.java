import java.util.Scanner;
public class ideas
{
    public static void main(String[] args)
    {
        Scanner kbReader = new Scanner(System.in);
        int i = (int) (Math.random() * 3);
        int[] arr = new int[5];
        arr[i] = 1; // ship is being filled in the random cell with value 1
        for (int k = 0; k < 3; k++) //
        {
            arr[i+k] = 1;
        }
        //int ii = (int) (Math.random() * 5);
        //arr[ii] = 2;
        System.out.println("answers are " + i);

        int hitcounter = 0;
        int killcounter = 0;

        while (hitcounter < 3)
        {
            System.out.println("Enter a number, 0 through 4. ");
            int guess = kbReader.nextInt();
            if (guess > 4)
            {
                System.out.println("guess again");
            }
            else if (arr[guess] == 1)
            {
                System.out.println("hit");
                hitcounter++;
                killcounter++;
                if (killcounter == 2)
                {
                    System.out.println("you win");
                    break;
                }
            }
            else
            {
                System.out.println("miss");
            }
        }
    }
}