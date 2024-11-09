import java.util.Random;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        int[][] visibleField = new int[7][7];
        int[][] hiddenField = new int[7][7];

        int oneSquareShips = 0;

        outerLoop:
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                hiddenField[i][j] = rand.nextInt(2);
                if (hiddenField[i][j] == 1) {
                    oneSquareShips++;

                }
                if (oneSquareShips == 3) break outerLoop;
            }
        }

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(hiddenField[i][j] + "  ");
            }
            System.out.println();
        }
    }
}