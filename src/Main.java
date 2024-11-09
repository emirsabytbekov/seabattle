import java.util.Random;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        int[][] visibleField = new int[7][7];
        int[][] privateField = new int[7][7];

        int oneSquareShips = 0;
        int twoSquareShips = 0;
        int threeSquareShips = 0;

        createOneSquareShips(privateField, rand, oneSquareShips);

        createTwoSquareShips(privateField, rand, twoSquareShips);

        createThreeSquareShip(privateField, rand, threeSquareShips);






        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(privateField[i][j] + "  ");
            }
            System.out.println();
        }
    }

    public static void createOneSquareShips (int[][] privateField, Random rand, int oneSquareShips) {

        createOneSquareShipsLoop:
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {

                if (privateField[i][j] == 0) {
                    privateField[i][j] = rand.nextInt(2);

                    if (privateField[i][j] == 1) {
                        oneSquareShips++;

                        if (i > 0 && j > 0) privateField[i-1][j-1] = 2;
                        if (i > 0)          privateField[i-1][j] = 2;
                        if (i > 0 && j < 6) privateField[i-1][j+1] = 2;
                        if (j < 6)          privateField[i][j+1] = 2;
                        if (i < 6 && j < 6) privateField[i+1][j+1] = 2;
                        if (i < 6)          privateField[i+1][j] = 2;
                        if (i < 6 && j > 0) privateField[i+1][j-1] = 2;
                        if (j > 0)          privateField[i][j-1] = 2;
                    }

                    if (oneSquareShips == 4) {
                        break createOneSquareShipsLoop;
                    }
                }

            }
        }
    }




    public static void createTwoSquareShips (int[][] privateField, Random rand, int twoSquareShips) {
        createTwoSquareShipsLoop:
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {

                if (privateField[i][j] == 0) {
                    privateField[i][j] = rand.nextInt(2);

                    if (privateField[i][j] == 1) {
                        int shipLength = 1;

                        if (j > 0) {
                            if (privateField[i][j-1] == 0) {
                                privateField[i][j-1] = rand.nextInt(2);

                                if (privateField[i][j-1] == 1) {
                                    shipLength++;
                                    twoSquareShips++;

                                    if (i > 0 && j > 1) privateField[i-1][j-2] = 2;
                                    if (i > 0)          privateField[i-1][j-1] = 2;
                                    if (i > 0)          privateField[i-1][j] = 2;
                                    if (i > 0 && j < 6) privateField[i-1][j+1] = 2;
                                    if (j < 6)          privateField[i][j+1] = 2;
                                    if (i < 6 && j < 6) privateField[i+1][j+1] = 2;
                                    if (i < 6)          privateField[i+1][j] = 2;
                                    if (i < 6)          privateField[i+1][j-1] = 2;
                                    if (i < 6 && j > 1) privateField[i+1][j-2] = 2;
                                    if (j > 1)          privateField[i][j-2] = 2;
                                }
                            }
                        }

                        if (twoSquareShips == 2) break createTwoSquareShipsLoop;

                        if (shipLength < 2) {
                            if (i > 0) {
                                if (privateField[i-1][j] == 0) {
                                    privateField[i-1][j] = rand.nextInt(2);

                                    if (privateField[i-1][j] == 1) {
                                        shipLength++;
                                        twoSquareShips++;

                                        if (i > 1 && j > 0) privateField[i-2][j-1] = 2;
                                        if (i > 1)          privateField[i-2][j] = 2;
                                        if (i > 1 && j < 6) privateField[i-2][j+1] = 2;
                                        if (j < 6)          privateField[i-1][j+1] = 2;
                                        if (j < 6)          privateField[i][j+1] = 2;
                                        if (i < 6 && j < 6) privateField[i+1][j+1] = 2;
                                        if (i < 6)          privateField[i+1][j] = 2;
                                        if (i < 6 && j > 0) privateField[i+1][j-1] = 2;
                                        if (j > 0)          privateField[i][j-1] = 2;
                                        if (j > 0)          privateField[i-1][j-1] = 2;
                                    }
                                }
                            }
                        }

                        if (twoSquareShips == 2) break createTwoSquareShipsLoop;

                        if (shipLength < 2) {
                            if (j < 6) {
                                if (privateField[i][j+1] == 0) {
                                    privateField[i][j+1] = rand.nextInt(2);

                                    if (privateField[i][j+1] == 1) {
                                        shipLength++;
                                        twoSquareShips++;

                                        if (i > 0 && j > 0) privateField[i-1][j-1] = 2;
                                        if (i > 0)          privateField[i-1][j] = 2;
                                        if (i > 0)          privateField[i-1][j+1] = 2;
                                        if (i > 0 && j < 5) privateField[i-1][j+2] = 2;
                                        if (j < 5)          privateField[i][j+2] = 2;
                                        if (i < 6 && j < 5) privateField[i+1][j+2] = 2;
                                        if (i < 6)          privateField[i+1][j+1] = 2;
                                        if (i < 6)          privateField[i+1][j] = 2;
                                        if (i < 6 && j > 0) privateField[i+1][j-1] = 2;
                                        if (j > 0)          privateField[i][j-1] = 2;
                                    }
                                }
                            }
                        }

                        if (twoSquareShips == 2) break createTwoSquareShipsLoop;

                        if (shipLength < 2) {
                            if (i < 6) {
                                if (privateField[i+1][j] == 0) {
                                    privateField[i+1][j] = rand.nextInt(2);

                                    if (privateField[i+1][j] == 1) {
                                        shipLength++;
                                        twoSquareShips++;

                                        if (i > 0 && j > 0) privateField[i-1][j-1] = 2;
                                        if (i > 0)          privateField[i-1][j] = 2;
                                        if (i > 0 && j < 6) privateField[i-1][j+1] = 2;
                                        if (j < 6)          privateField[i][j+1] = 2;
                                        if (j < 6)          privateField[i+1][j+1] = 2;
                                        if (i < 5 && j < 6) privateField[i+2][j+1] = 2;
                                        if (i < 5)          privateField[i+2][j] = 2;
                                        if (i < 5 && j > 0) privateField[i+2][j-1] = 2;
                                        if (j > 0)          privateField[i+1][j-1] = 2;
                                        if (j > 0)          privateField[i][j-1] = 2;
                                    }
                                }
                            }
                        }
                        if (twoSquareShips == 2) break createTwoSquareShipsLoop;

                        if (shipLength < 2) {
                            privateField[i][j] = 0;
                        }

                    }
                }
            }
        }
    }




    public static void createThreeSquareShip (int[][] privateField, Random rand, int threeSquareShips) {
        createThreeSquareShipLoop:
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {

                if (privateField[i][j] == 0) {
                    privateField[i][j] = rand.nextInt(2);

                    if (privateField[i][j] == 1) {

                        if (j > 1) {
                            if (privateField[i][j-1] == 0 && privateField[i][j-2] == 0) {

                                int element = rand.nextInt(2);
                                privateField[i][j-1] = element;
                                privateField[i][j-2] = element;

                                if (privateField[i][j-2] == 1) {
                                    threeSquareShips++;

                                    if (i > 0 && j > 2) privateField[i-1][j-3] = 2;
                                    if (i > 0) {
                                        privateField[i-1][j-2] = 2;
                                        privateField[i-1][j-1] = 2;
                                        privateField[i-1][j] = 2;
                                    }
                                    if (i > 0 && j < 6) privateField[i-1][j+1] = 2;
                                    if (j < 6)          privateField[i][j+1] = 2;
                                    if (i < 6 && j < 6) privateField[i+1][j+1] = 2;
                                    if (i < 6) {
                                        privateField[i+1][j] = 2;
                                        privateField[i+1][j-1] = 2;
                                        privateField[i+1][j-2] = 2;
                                    }
                                    if (i < 6 && j > 2) privateField[i+1][j-3] = 2;
                                    if (j > 2)          privateField[i][j-3] = 2;

                                }

                            }
                        }

                        if (threeSquareShips == 1) break createThreeSquareShipLoop;

                        if (i > 1) {
                            if (privateField[i-1][j] == 0 && privateField[i-2][j] == 0) {

                                int element = rand.nextInt(2);
                                privateField[i-1][j] = element;
                                privateField[i-2][j] = element;

                                if (privateField[i-2][j] == 1) {
                                    threeSquareShips++;

                                    if (i > 2 && j > 0) privateField[i-3][j-1] = 2;
                                    if (i > 2)          privateField[i-3][j] = 2;
                                    if (i > 2 && j < 6) privateField[i-3][j+1] = 2;
                                    if (j < 6) {
                                        privateField[i-2][j+1] = 2;
                                        privateField[i-1][j+1] = 2;
                                        privateField[i][j+1] = 2;
                                    }
                                    if (i < 6 && j < 6) privateField[i+1][j+1] = 2;
                                    if (i < 6)          privateField[i+1][j] = 2;
                                    if (i < 6 && j > 0) privateField[i+1][j-1] = 2;
                                    if (j > 0) {
                                        privateField[i][j-1] = 2;
                                        privateField[i-1][j-1] = 2;
                                        privateField[i-2][j-1] = 2;
                                    }
                                }
                            }
                        }

                        if (threeSquareShips == 1) break createThreeSquareShipLoop;

                        if (j < 5) {
                            if (privateField[i][j+1] == 0 && privateField[i][j+2] == 0) {

                                int element = rand.nextInt(2);
                                privateField[i][j+1] = element;
                                privateField[i][j+2] = element;

                                if (privateField[i][j+2] == 1) {
                                    threeSquareShips++;

                                    if (i > 0 && j > 0) privateField[i-1][j-1] = 2;
                                    if (i > 0) {
                                        privateField[i-1][j] = 2;
                                        privateField[i-1][j+1] = 2;
                                        privateField[i-1][j+2] = 2;
                                    }
                                    if (i > 0 && j < 4) privateField[i-1][j+3] = 2;
                                    if (j < 4)          privateField[i][j+3] = 2;
                                    if (i < 6 && j < 4) privateField[i+1][j+3] = 2;
                                    if (i < 6) {
                                        privateField[i+1][j+2] = 2;
                                        privateField[i+1][j+1] = 2;
                                        privateField[i+1][j] = 2;
                                    }
                                    if (i < 6 && j > 0) privateField[i+1][j-1] = 2;
                                    if (j > 0)          privateField[i][j-1] = 2;
                                }
                            }
                        }

                        if (threeSquareShips == 1) break createThreeSquareShipLoop;

                        if (i < 5) {
                            if (privateField[i+1][j] == 0 && privateField[i+2][j] == 0) {

                                int element = rand.nextInt(2);
                                privateField[i+1][j] = element;
                                privateField[i+2][j] = element;

                                if (privateField[i+2][j] == 1) {
                                    threeSquareShips++;

                                    if (i > 0 && j > 0) privateField[i-1][j-1] = 2;
                                    if (i > 0)          privateField[i-1][j] = 2;
                                    if (i > 0 && j < 6) privateField[i-1][j+1] = 2;
                                    if (j < 6) {
                                        privateField[i][j+1] = 2;
                                        privateField[i+1][j+1] = 2;
                                        privateField[i+2][j+1] = 2;
                                    }
                                    if (i < 4 && j < 6) privateField[i+3][j+1] = 2;
                                    if (i < 4)          privateField[i+3][j] = 2;
                                    if (i < 4 && j > 0) privateField[i+3][j-1] = 2;
                                    if (j > 0) {
                                        privateField[i+2][j-1] = 2;
                                        privateField[i+1][j-1] = 2;
                                        privateField[i][j-1] = 2;
                                    }
                                }
                            }
                        }

                        if (threeSquareShips == 1) break createThreeSquareShipLoop;

                    }
                }
            }
        }
    }
}