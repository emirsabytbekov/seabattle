import java.util.Random;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        int[][] visibleField = new int[7][7];
        int[][] hiddenField = new int[7][7];

        int oneSquareShips = 0;
        int twoSquareShips = 0;
        int threeSquareShips = 0;

//        createOneSquareShipsLoop:
//        for (int i = 0; i < 7; i++) {
//            for (int j = 0; j < 7; j++) {
//                if ( (j > 0 && hiddenField[i][j-1] == 1) || (i > 0 && ((j > 0 && hiddenField[i-1][j-1] == 1) || hiddenField[i-1][j] == 1 || (j<6 && hiddenField[i-1][j+1] == 1) ) ) ) {
//                    hiddenField[i][j] = 0;
//                }
//                else hiddenField[i][j] = rand.nextInt(2);
//
//                if (hiddenField[i][j] == 1) {
//                    oneSquareShips++;
//                }
//
//                if (oneSquareShips == 4) break createOneSquareShipsLoop;
//            }
//        }

//        createTwoSquareShipsLoop:
//        for (int i = 0; i < 7; i++) {
//            for (int j = 0; j < 7; j++) {
//
//                if ( (j > 0 && hiddenField[i][j-1] == 1) || (i > 0 && ((j > 0 && hiddenField[i-1][j-1] == 1) || hiddenField[i-1][j] == 1 || (j < 6 && hiddenField[i-1][j+1] == 1) ) ) )
//                {
//                    hiddenField[i][j] = 0;
//                }
//
//                else
//                {
//                    hiddenField[i][j] = rand.nextInt(2);
//                    if (hiddenField[i][j] == 1) {
//
//                        if ( (j > 1 && hiddenField[i][j-2] == 1) || (i > 0 && j > 1 && hiddenField[i-1][j-2] == 0) )
//                        {
//                            hiddenField[i][j] = 0;
//                        }
//                        else
//                        {
//                            hiddenField[i][j-1] = rand.nextInt(2);
//
//                            if (hiddenField[i][j-1] == 1) {
//                                twoSquareShips++;
//                                if (twoSquareShips == 2) break createTwoSquareShipsLoop;
//                            }
//                        }
//
//                        if ((i > 1 && j > 0 && hiddenField[i-2][j-1] == 1) && (hiddenField[i-2][j] == 0) && (j < 6 && hiddenField[i-2][j+1] == 0)  )
//                        {
//                            hiddenField[i-1][j] = rand.nextInt(2);
//
//                            if (hiddenField[i-1][j] == 1) {
//                                twoSquareShips++;
//                                if (twoSquareShips == 2) break createTwoSquareShipsLoop;
//                            }
//                        }
//                        else if (j < 6 && (i > 0 && j < 5 && hiddenField[i-1][j+2] == 0))
//                        {
//                            hiddenField[i][j+1] = rand.nextInt(2);
//
//                            if (hiddenField[i][j+1] == 1) {
//                                twoSquareShips++;
//                                if (twoSquareShips == 2) break createTwoSquareShipsLoop;
//                            }
//                        }
//                        else if (i < 6) {
//                            hiddenField[i+1][j] = rand.nextInt(2);
//
//                            if (hiddenField[i+1][j] == 1) {
//                                twoSquareShips++;
//                                if (twoSquareShips == 2) break createTwoSquareShipsLoop;
//                            }
//                        }
//                        else {
//                            hiddenField[i][j] = 0;
//                        }
//                    }
//                }
//
//            }
//        }


        createOneSquareShipsLoop:
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {

//                if (j > 0) {
//                    if (i > 0) {
//                        if (hiddenField[i-1][j-1] == 1) hiddenField[i][j] = 2;
//                    }
//                    else if (i < 6) {
//                        if (hiddenField[i+1][j-1] == 1) hiddenField[i][j] = 2;
//                    }
//                    else if (hiddenField[i][j-1] == 1) hiddenField[i][j] = 2;
//                }
//
//                else if (i > 0) {
//                    if (j > 0) {
//                        if (hiddenField[i-1][j-1] == 1) hiddenField[i][j] = 2;
//                    }
//                    else if (j < 6) {
//                        if (hiddenField[i-1][j+1] == 1) hiddenField[i][j] = 2;
//                    }
//                    else if (hiddenField[i-1][j] == 1) hiddenField[i][j] = 2;
//                }
//
//                else if (j < 6) {
//                    if (i > 0) {
//                        if (hiddenField[i-1][j+1] == 1) hiddenField[i][j] = 2;
//                    }
//                    else if (i < 6) {
//                        if (hiddenField[i+1][j+1] == 1) hiddenField[i][j] = 2;
//                    }
//                    else if (hiddenField[i][j+1] == 1) hiddenField[i][j] = 2;
//                }
//
//                else if (i < 6) {
//                    if (j > 0) {
//                        if (hiddenField[i+1][j-1] == 1) hiddenField[i][j] = 0;
//                    }
//                    else if (j < 6) {
//                        if (hiddenField[i+1][j+1] == 1) hiddenField[i][j] = 0;
//                    }
//                    else if (hiddenField[i+1][j] == 1) hiddenField[i][j] = 0;
//                }

                if (hiddenField[i][j] != 2) {
                    hiddenField[i][j] = rand.nextInt(2);

                    if (hiddenField[i][j] == 1) {
                        oneSquareShips++;

                        if (i > 0 && j > 0) hiddenField[i-1][j-1] = 2;
                        if (i > 0)          hiddenField[i-1][j] = 2;
                        if (i > 0 && j < 6) hiddenField[i-1][j+1] = 2;
                        if (j < 6)          hiddenField[i][j+1] = 2;
                        if (i < 6 && j < 6) hiddenField[i+1][j+1] = 2;
                        if (i < 6)          hiddenField[i+1][j] = 2;
                        if (i < 6 && j > 0) hiddenField[i+1][j-1] = 2;
                        if (j > 0)          hiddenField[i][j-1] = 2;
                    }

                    if (oneSquareShips == 4) {
                        break createOneSquareShipsLoop;
                    }
                }

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