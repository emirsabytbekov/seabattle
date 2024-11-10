import java.util.Random;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        int[][] visibleField = new int[7][7];
        int[][] privateField = new int[7][7];

        String [][] designedPrivateField = new String[7][7];

        int oneSquareShips = 0;
        int twoSquareShips = 0;
        int threeSquareShips = 0;

        randomlySpawnShips(rand, privateField, oneSquareShips, twoSquareShips, threeSquareShips);

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (privateField[i][j] == 1) {
                    designedPrivateField[i][j] = "\u26f5";
                }
                else {
                    designedPrivateField[i][j] = "\ud83c\udf0a";
                }
            }
        }

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(designedPrivateField[i][j]);
            }
            System.out.println();
        }
    }

    public static void spawnOneSquareShips(int[][] privateField, Random rand, int oneSquareShips) {

        createOneSquareShipsLoop:
        for (int column = 0; column < 7; column++) {
            for (int row = 0; row < 7; row++) {

                if (privateField[column][row] == 0) {
                    privateField[column][row] = rand.nextInt(2);

                    if (privateField[column][row] == 1) {
                        oneSquareShips++;

                        if (column > 0 && row > 0) privateField[column -1][row -1] = 2;
                        if (column > 0)          privateField[column -1][row] = 2;
                        if (column > 0 && row < 6) privateField[column -1][row +1] = 2;
                        if (row < 6)          privateField[column][row +1] = 2;
                        if (column < 6 && row < 6) privateField[column +1][row +1] = 2;
                        if (column < 6)          privateField[column +1][row] = 2;
                        if (column < 6 && row > 0) privateField[column +1][row -1] = 2;
                        if (row > 0)          privateField[column][row -1] = 2;
                    }

                    if (oneSquareShips == 4) {
                        break createOneSquareShipsLoop;
                    }
                }

            }
        }
    }




    public static void spawnTwoSquareShips(int[][] privateField, Random rand, int twoSquareShips) {
        createTwoSquareShipsLoop:
        for (int column = 0; column < 7; column++) {
            for (int row = 0; row < 7; row++) {

                if (privateField[column][row] == 0) {
                    privateField[column][row] = rand.nextInt(2);

                    if (privateField[column][row] == 1) {
                        int shipLength = 1;

                        if (row > 0) {
                            if (privateField[column][row -1] == 0) {
                                privateField[column][row -1] = rand.nextInt(2);

                                if (privateField[column][row -1] == 1) {
                                    shipLength++;
                                    twoSquareShips++;

                                    if (column > 0 && row > 1) privateField[column -1][row -2] = 2;
                                    if (column > 0)          privateField[column -1][row -1] = 2;
                                    if (column > 0)          privateField[column -1][row] = 2;
                                    if (column > 0 && row < 6) privateField[column -1][row +1] = 2;
                                    if (row < 6)          privateField[column][row +1] = 2;
                                    if (column < 6 && row < 6) privateField[column +1][row +1] = 2;
                                    if (column < 6)          privateField[column +1][row] = 2;
                                    if (column < 6)          privateField[column +1][row -1] = 2;
                                    if (column < 6 && row > 1) privateField[column +1][row -2] = 2;
                                    if (row > 1)          privateField[column][row -2] = 2;
                                }
                            }
                        }

                        if (twoSquareShips == 2) break createTwoSquareShipsLoop;

                        if (shipLength < 2) {
                            if (column > 0) {
                                if (privateField[column -1][row] == 0) {
                                    privateField[column -1][row] = rand.nextInt(2);

                                    if (privateField[column -1][row] == 1) {
                                        shipLength++;
                                        twoSquareShips++;

                                        if (column > 1 && row > 0) privateField[column -2][row -1] = 2;
                                        if (column > 1)          privateField[column -2][row] = 2;
                                        if (column > 1 && row < 6) privateField[column -2][row +1] = 2;
                                        if (row < 6)          privateField[column -1][row +1] = 2;
                                        if (row < 6)          privateField[column][row +1] = 2;
                                        if (column < 6 && row < 6) privateField[column +1][row +1] = 2;
                                        if (column < 6)          privateField[column +1][row] = 2;
                                        if (column < 6 && row > 0) privateField[column +1][row -1] = 2;
                                        if (row > 0)          privateField[column][row -1] = 2;
                                        if (row > 0)          privateField[column -1][row -1] = 2;
                                    }
                                }
                            }
                        }

                        if (twoSquareShips == 2) break createTwoSquareShipsLoop;

                        if (shipLength < 2) {
                            if (row < 6) {
                                if (privateField[column][row +1] == 0) {
                                    privateField[column][row +1] = rand.nextInt(2);

                                    if (privateField[column][row +1] == 1) {
                                        shipLength++;
                                        twoSquareShips++;

                                        if (column > 0 && row > 0) privateField[column -1][row -1] = 2;
                                        if (column > 0)          privateField[column -1][row] = 2;
                                        if (column > 0)          privateField[column -1][row +1] = 2;
                                        if (column > 0 && row < 5) privateField[column -1][row +2] = 2;
                                        if (row < 5)          privateField[column][row +2] = 2;
                                        if (column < 6 && row < 5) privateField[column +1][row +2] = 2;
                                        if (column < 6)          privateField[column +1][row +1] = 2;
                                        if (column < 6)          privateField[column +1][row] = 2;
                                        if (column < 6 && row > 0) privateField[column +1][row -1] = 2;
                                        if (row > 0)          privateField[column][row -1] = 2;
                                    }
                                }
                            }
                        }

                        if (twoSquareShips == 2) break createTwoSquareShipsLoop;

                        if (shipLength < 2) {
                            if (column < 6) {
                                if (privateField[column +1][row] == 0) {
                                    privateField[column +1][row] = rand.nextInt(2);

                                    if (privateField[column +1][row] == 1) {
                                        shipLength++;
                                        twoSquareShips++;

                                        if (column > 0 && row > 0) privateField[column -1][row -1] = 2;
                                        if (column > 0)          privateField[column -1][row] = 2;
                                        if (column > 0 && row < 6) privateField[column -1][row +1] = 2;
                                        if (row < 6)          privateField[column][row +1] = 2;
                                        if (row < 6)          privateField[column +1][row +1] = 2;
                                        if (column < 5 && row < 6) privateField[column +2][row +1] = 2;
                                        if (column < 5)          privateField[column +2][row] = 2;
                                        if (column < 5 && row > 0) privateField[column +2][row -1] = 2;
                                        if (row > 0)          privateField[column +1][row -1] = 2;
                                        if (row > 0)          privateField[column][row -1] = 2;
                                    }
                                }
                            }
                        }
                        if (twoSquareShips == 2) break createTwoSquareShipsLoop;

                        if (shipLength < 2) {
                            privateField[column][row] = 0;
                        }

                    }
                }
            }
        }
    }




    public static void spawnThreeSquareShip(int[][] privateField, Random rand, int threeSquareShips) {
        createThreeSquareShipLoop:
        for (int column = 0; column < 7; column++) {
            for (int row = 0; row < 7; row++) {

                if (privateField[column][row] == 0) {
                    privateField[column][row] = rand.nextInt(2);

                    if (privateField[column][row] == 1) {

                        if (row > 1) {
                            if (privateField[column][row -1] == 0 && privateField[column][row -2] == 0) {

                                int element = rand.nextInt(2);
                                privateField[column][row -1] = element;
                                privateField[column][row -2] = element;

                                if (privateField[column][row -2] == 1) {
                                    threeSquareShips++;

                                    if (column > 0 && row > 2) privateField[column -1][row -3] = 2;
                                    if (column > 0) {
                                        privateField[column -1][row -2] = 2;
                                        privateField[column -1][row -1] = 2;
                                        privateField[column -1][row] = 2;
                                    }
                                    if (column > 0 && row < 6) privateField[column -1][row +1] = 2;
                                    if (row < 6)          privateField[column][row +1] = 2;
                                    if (column < 6 && row < 6) privateField[column +1][row +1] = 2;
                                    if (column < 6) {
                                        privateField[column +1][row] = 2;
                                        privateField[column +1][row -1] = 2;
                                        privateField[column +1][row -2] = 2;
                                    }
                                    if (column < 6 && row > 2) privateField[column +1][row -3] = 2;
                                    if (row > 2)          privateField[column][row -3] = 2;

                                }

                            }
                        }

                        if (threeSquareShips == 1) break createThreeSquareShipLoop;

                        if (column > 1) {
                            if (privateField[column -1][row] == 0 && privateField[column -2][row] == 0) {

                                int element = rand.nextInt(2);
                                privateField[column -1][row] = element;
                                privateField[column -2][row] = element;

                                if (privateField[column -2][row] == 1) {
                                    threeSquareShips++;

                                    if (column > 2 && row > 0) privateField[column -3][row -1] = 2;
                                    if (column > 2)          privateField[column -3][row] = 2;
                                    if (column > 2 && row < 6) privateField[column -3][row +1] = 2;
                                    if (row < 6) {
                                        privateField[column -2][row +1] = 2;
                                        privateField[column -1][row +1] = 2;
                                        privateField[column][row +1] = 2;
                                    }
                                    if (column < 6 && row < 6) privateField[column +1][row +1] = 2;
                                    if (column < 6)          privateField[column +1][row] = 2;
                                    if (column < 6 && row > 0) privateField[column +1][row -1] = 2;
                                    if (row > 0) {
                                        privateField[column][row -1] = 2;
                                        privateField[column -1][row -1] = 2;
                                        privateField[column -2][row -1] = 2;
                                    }
                                }
                            }
                        }

                        if (threeSquareShips == 1) break createThreeSquareShipLoop;

                        if (row < 5) {
                            if (privateField[column][row +1] == 0 && privateField[column][row +2] == 0) {

                                int element = rand.nextInt(2);
                                privateField[column][row +1] = element;
                                privateField[column][row +2] = element;

                                if (privateField[column][row +2] == 1) {
                                    threeSquareShips++;

                                    if (column > 0 && row > 0) privateField[column -1][row -1] = 2;
                                    if (column > 0) {
                                        privateField[column -1][row] = 2;
                                        privateField[column -1][row +1] = 2;
                                        privateField[column -1][row +2] = 2;
                                    }
                                    if (column > 0 && row < 4) privateField[column -1][row +3] = 2;
                                    if (row < 4)          privateField[column][row +3] = 2;
                                    if (column < 6 && row < 4) privateField[column +1][row +3] = 2;
                                    if (column < 6) {
                                        privateField[column +1][row +2] = 2;
                                        privateField[column +1][row +1] = 2;
                                        privateField[column +1][row] = 2;
                                    }
                                    if (column < 6 && row > 0) privateField[column +1][row -1] = 2;
                                    if (row > 0)          privateField[column][row -1] = 2;
                                }
                            }
                        }

                        if (threeSquareShips == 1) break createThreeSquareShipLoop;

                        if (column < 5) {
                            if (privateField[column +1][row] == 0 && privateField[column +2][row] == 0) {

                                int element = rand.nextInt(2);
                                privateField[column +1][row] = element;
                                privateField[column +2][row] = element;

                                if (privateField[column +2][row] == 1) {
                                    threeSquareShips++;

                                    if (column > 0 && row > 0) privateField[column -1][row -1] = 2;
                                    if (column > 0)          privateField[column -1][row] = 2;
                                    if (column > 0 && row < 6) privateField[column -1][row +1] = 2;
                                    if (row < 6) {
                                        privateField[column][row +1] = 2;
                                        privateField[column +1][row +1] = 2;
                                        privateField[column +2][row +1] = 2;
                                    }
                                    if (column < 4 && row < 6) privateField[column +3][row +1] = 2;
                                    if (column < 4)          privateField[column +3][row] = 2;
                                    if (column < 4 && row > 0) privateField[column +3][row -1] = 2;
                                    if (row > 0) {
                                        privateField[column +2][row -1] = 2;
                                        privateField[column +1][row -1] = 2;
                                        privateField[column][row -1] = 2;
                                    }
                                }
                            }
                        }

                        if (threeSquareShips == 1) break createThreeSquareShipLoop;

                        if (threeSquareShips < 1) {
                            privateField[column][row] = 0;
                        }

                    }
                }
            }
        }
    }

    public static void randomlySpawnShips (Random rand, int[][] privateField, int oneSquareShips, int twoSquareShips, int threeSquareShips) {
        int actionsOrder = rand.nextInt(6);

        switch (actionsOrder) {
            case 0:
                spawnOneSquareShips(privateField, rand, oneSquareShips);
                spawnTwoSquareShips(privateField, rand, twoSquareShips);
                spawnThreeSquareShip(privateField, rand, threeSquareShips);
                break;
            case 1:
                spawnOneSquareShips(privateField, rand, oneSquareShips);
                spawnThreeSquareShip(privateField, rand, threeSquareShips);
                spawnTwoSquareShips(privateField, rand, twoSquareShips);
                break;
            case 2:
                spawnTwoSquareShips(privateField, rand, twoSquareShips);
                spawnOneSquareShips(privateField, rand, oneSquareShips);
                spawnThreeSquareShip(privateField, rand, threeSquareShips);
                break;
            case 3:
                spawnTwoSquareShips(privateField, rand, twoSquareShips);
                spawnThreeSquareShip(privateField, rand, threeSquareShips);
                spawnOneSquareShips(privateField, rand, oneSquareShips);
                break;
            case 4:
                spawnThreeSquareShip(privateField, rand, threeSquareShips);
                spawnOneSquareShips(privateField, rand, oneSquareShips);
                spawnTwoSquareShips(privateField, rand, twoSquareShips);
                break;
            case 5:
                spawnThreeSquareShip(privateField, rand, threeSquareShips);
                spawnTwoSquareShips(privateField, rand, twoSquareShips);
                spawnOneSquareShips(privateField, rand, oneSquareShips);
                break;
        }
    }
}