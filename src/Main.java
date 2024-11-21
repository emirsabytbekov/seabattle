import java.util.Random;
import java.util.Scanner;
public class Main {

    static final int emptyCell = 0;
    static final int ship = 1;
    static final int nearShip = 2;

    static final String shipEmoji = "\u26f5 ";
    static final String waterEmoji = "\ud83c\udf0a ";
    static final String fogEmoji = "\ud83c\udf2b\ufe0f ";
    static final String hitEmoji = "\ud83d\udca5 ";
    static final String sunkEmoji = "\u2620\ufe0f ";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        String[][] visibleField = new String[7][7];
        int[][] privateField = new int[7][7];

        String [][] designedPrivateField = new String[7][7];

        int oneSquareShips = 0;
        int twoSquareShips = 0;
        int threeSquareShips = 0;

        randomlySpawnShips(rand, privateField, oneSquareShips, twoSquareShips, threeSquareShips);

        for (int row = 0; row < 7; row++) {
            for (int column = 0; column < 7; column++) {
                if (privateField[row][column] == ship) {
                    designedPrivateField[row][column] = shipEmoji;
                }
                else {
                    designedPrivateField[row][column] = waterEmoji;
                }
            }
        }



        for (int row = 0; row < 7; row++) {
            for (int column = 0; column < 7; column++) {
                visibleField[row][column] = fogEmoji;
            }
        }

        for (int row = 0; row < 7; row++) {
            for (int column = 0; column < 7; column++) {
                System.out.print(visibleField[row][column]);
            }
            System.out.println();
        }


        int guessRow = 0;
        int guessColumn = 0;

        while(true) {
            guessRow = sc.nextInt() - 1;
            guessColumn = sc.nextInt() - 1;

            visibleField[guessRow][guessColumn] = designedPrivateField[guessRow][guessColumn];

            if (privateField[guessRow][guessColumn] == ship) {

                int sunk = 0;
                int hit = 1;
                int unknownYet = 2;
                int hitOrSunk = unknownYet;

                boolean isStillAlive = true;

                if (guessRow > 0) {
                    hitOrSunk = privateField[guessRow - 1][guessColumn] == ship ? hit : unknownYet;
                }
                if (hitOrSunk == unknownYet && guessColumn < 6) {
                    hitOrSunk = privateField[guessRow][guessColumn +1] == ship ? hit : unknownYet;
                }
                if (hitOrSunk == unknownYet && guessRow < 6) {
                    hitOrSunk = privateField[guessRow + 1][guessColumn] == ship ? hit : unknownYet;
                }
                if (hitOrSunk == unknownYet && guessColumn > 0) {
                    hitOrSunk = privateField[guessRow][guessColumn - 1] == ship ? hit : sunk;
                }

                visibleField[guessRow][guessColumn] = hitOrSunk == hit ? hitEmoji : sunkEmoji;

            }

            for (int row = 0; row < 7; row++) {
                for (int column = 0; column < 7; column++) {
                    System.out.print(visibleField[row][column]);
                }
                System.out.println();
            }

        }



//        for (int i = 0; i < 7; i++) {
//            for (int j = 0; j < 7; j++) {
//                System.out.print(designedPrivateField[i][j]);
//            }
//            System.out.println();
//        }
    }


    public static void spawnOneSquareShips(int[][] privateField, Random rand, int oneSquareShips) {

        createOneSquareShipsLoop:
        for (int row = 0; row < 7; row++) {
            for (int column = 0; column < 7; column++) {

                if (privateField[row][column] == emptyCell) {
                    privateField[row][column] = rand.nextInt(2);

                    if (privateField[row][column] == ship) {
                        oneSquareShips++;

                        if (row > 0 && column > 0) privateField[row -1][column -1] = nearShip;
                        if (row > 0)               privateField[row -1][column] = nearShip;
                        if (row > 0 && column < 6) privateField[row -1][column +1] = nearShip;
                        if (column < 6)            privateField[row][column +1] = nearShip;
                        if (row < 6 && column < 6) privateField[row +1][column +1] = nearShip;
                        if (row < 6)               privateField[row +1][column] = nearShip;
                        if (row < 6 && column > 0) privateField[row +1][column -1] = nearShip;
                        if (column > 0)            privateField[row][column -1] = nearShip;
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
        for (int row = 0; row < 7; row++) {
            for (int column = 0; column < 7; column++) {

                if (privateField[row][column] == emptyCell) {
                    privateField[row][column] = rand.nextInt(2);

                    if (privateField[row][column] == ship) {
                        int shipLength = 1;

                        if (column > 0) {
                            if (privateField[row][column -1] == emptyCell) {
                                privateField[row][column -1] = rand.nextInt(2);

                                if (privateField[row][column -1] == ship) {
                                    shipLength++;
                                    twoSquareShips++;

                                    if (row > 0 && column > 1) privateField[row -1][column -2] = nearShip;
                                    if (row > 0)               privateField[row -1][column -1] = nearShip;
                                    if (row > 0)               privateField[row -1][column] = nearShip;
                                    if (row > 0 && column < 6) privateField[row -1][column +1] = nearShip;
                                    if (column < 6)            privateField[row][column +1] = nearShip;
                                    if (row < 6 && column < 6) privateField[row +1][column +1] = nearShip;
                                    if (row < 6)               privateField[row +1][column] = nearShip;
                                    if (row < 6)               privateField[row +1][column -1] = nearShip;
                                    if (row < 6 && column > 1) privateField[row +1][column -2] = nearShip;
                                    if (column > 1)            privateField[row][column -2] = nearShip;
                                }
                            }
                        }

                        if (twoSquareShips == 2) break createTwoSquareShipsLoop;

                        if (shipLength < 2) {
                            if (row > 0) {
                                if (privateField[row -1][column] == emptyCell) {
                                    privateField[row -1][column] = rand.nextInt(2);

                                    if (privateField[row -1][column] == ship) {
                                        shipLength++;
                                        twoSquareShips++;

                                        if (row > 1 && column > 0) privateField[row -2][column -1] = nearShip;
                                        if (row > 1)               privateField[row -2][column] = nearShip;
                                        if (row > 1 && column < 6) privateField[row -2][column +1] = nearShip;
                                        if (column < 6)            privateField[row -1][column +1] = nearShip;
                                        if (column < 6)            privateField[row][column +1] = nearShip;
                                        if (row < 6 && column < 6) privateField[row +1][column +1] = nearShip;
                                        if (row < 6)               privateField[row +1][column] = nearShip;
                                        if (row < 6 && column > 0) privateField[row +1][column -1] = nearShip;
                                        if (column > 0)            privateField[row][column -1] = nearShip;
                                        if (column > 0)            privateField[row -1][column -1] = nearShip;
                                    }
                                }
                            }
                        }

                        if (twoSquareShips == 2) break createTwoSquareShipsLoop;

                        if (shipLength < 2) {
                            if (column < 6) {
                                if (privateField[row][column +1] == emptyCell) {
                                    privateField[row][column +1] = rand.nextInt(2);

                                    if (privateField[row][column +1] == ship) {
                                        shipLength++;
                                        twoSquareShips++;

                                        if (row > 0 && column > 0) privateField[row -1][column -1] = nearShip;
                                        if (row > 0)               privateField[row -1][column] = nearShip;
                                        if (row > 0)               privateField[row -1][column +1] = nearShip;
                                        if (row > 0 && column < 5) privateField[row -1][column +2] = nearShip;
                                        if (column < 5)            privateField[row][column +2] = nearShip;
                                        if (row < 6 && column < 5) privateField[row +1][column +2] = nearShip;
                                        if (row < 6)               privateField[row +1][column +1] = nearShip;
                                        if (row < 6)               privateField[row +1][column] = nearShip;
                                        if (row < 6 && column > 0) privateField[row +1][column -1] = nearShip;
                                        if (column > 0)            privateField[row][column -1] = nearShip;
                                    }
                                }
                            }
                        }

                        if (twoSquareShips == 2) break createTwoSquareShipsLoop;

                        if (shipLength < 2) {
                            if (row < 6) {
                                if (privateField[row +1][column] == emptyCell) {
                                    privateField[row +1][column] = rand.nextInt(2);

                                    if (privateField[row +1][column] == ship) {
                                        shipLength++;
                                        twoSquareShips++;

                                        if (row > 0 && column > 0) privateField[row -1][column -1] = nearShip;
                                        if (row > 0)               privateField[row -1][column] = nearShip;
                                        if (row > 0 && column < 6) privateField[row -1][column +1] = nearShip;
                                        if (column < 6)            privateField[row][column +1] = nearShip;
                                        if (column < 6)            privateField[row +1][column +1] = nearShip;
                                        if (row < 5 && column < 6) privateField[row +2][column +1] = nearShip;
                                        if (row < 5)               privateField[row +2][column] = nearShip;
                                        if (row < 5 && column > 0) privateField[row +2][column -1] = nearShip;
                                        if (column > 0)            privateField[row +1][column -1] = nearShip;
                                        if (column > 0)            privateField[row][column -1] = nearShip;
                                    }
                                }
                            }
                        }
                        if (twoSquareShips == 2) break createTwoSquareShipsLoop;

                        if (shipLength < 2) {
                            privateField[row][column] = emptyCell;
                        }

                    }
                }
            }
        }
    }




    public static void spawnThreeSquareShip(int[][] privateField, Random rand, int threeSquareShips) {
        createThreeSquareShipLoop:
        for (int row = 0; row < 7; row++) {
            for (int column = 0; column < 7; column++) {

                if (privateField[row][column] == emptyCell) {
                    privateField[row][column] = rand.nextInt(2);

                    if (privateField[row][column] == ship) {

                        if (column > 1) {
                            if (privateField[row][column -1] == emptyCell && privateField[row][column -2] == emptyCell) {

                                boolean isSpawnShip = rand.nextInt(2) == ship;
                                if (isSpawnShip) {
                                    privateField[row][column - 1] = ship;
                                    privateField[row][column - 2] = ship;
                                }

                                if (privateField[row][column -2] == ship) {
                                    threeSquareShips++;

                                    if (row > 0 && column > 2) privateField[row -1][column -3] = emptyCell;
                                    if (row > 0) {
                                                               privateField[row -1][column -2] = nearShip;
                                                               privateField[row -1][column -1] = nearShip;
                                                               privateField[row -1][column] = nearShip;
                                    }
                                    if (row > 0 && column < 6) privateField[row -1][column +1] = nearShip;
                                    if (column < 6)            privateField[row][column +1] = nearShip;
                                    if (row < 6 && column < 6) privateField[row +1][column +1] = nearShip;
                                    if (row < 6) {
                                                               privateField[row +1][column] = nearShip;
                                                               privateField[row +1][column -1] = nearShip;
                                                               privateField[row +1][column -2] = nearShip;
                                    }
                                    if (row < 6 && column > 2) privateField[row +1][column -3] = nearShip;
                                    if (column > 2)            privateField[row][column -3] = nearShip;

                                }

                            }
                        }

                        if (threeSquareShips == 1) break createThreeSquareShipLoop;

                        if (row > 1) {
                            if (privateField[row -1][column] == emptyCell && privateField[row -2][column] == emptyCell) {

                                boolean isSpawnShip = rand.nextInt(2) == ship;
                                if (isSpawnShip) {
                                    privateField[row - 1][column] = ship;
                                    privateField[row - 2][column] = ship;
                                }

                                if (privateField[row - 2][column] == ship) {
                                    threeSquareShips++;

                                    if (row > 2 && column > 0) privateField[row -3][column -1] = nearShip;
                                    if (row > 2)               privateField[row -3][column] = nearShip;
                                    if (row > 2 && column < 6) privateField[row -3][column +1] = nearShip;
                                    if (column < 6) {
                                                               privateField[row -2][column +1] = nearShip;
                                                               privateField[row -1][column +1] = nearShip;
                                                               privateField[row][column +1] = nearShip;
                                    }
                                    if (row < 6 && column < 6) privateField[row +1][column +1] = nearShip;
                                    if (row < 6)               privateField[row +1][column] = nearShip;
                                    if (row < 6 && column > 0) privateField[row +1][column -1] = nearShip;
                                    if (column > 0) {
                                                               privateField[row][column -1] = nearShip;
                                                               privateField[row -1][column -1] = nearShip;
                                                               privateField[row -2][column -1] = nearShip;
                                    }
                                }
                            }
                        }

                        if (threeSquareShips == 1) break createThreeSquareShipLoop;

                        if (column < 5) {
                            if (privateField[row][column +1] == emptyCell && privateField[row][column +2] == emptyCell) {

                                boolean isSpawnShip = rand.nextInt(2) == ship;
                                if (isSpawnShip) {
                                    privateField[row][column + 1] = ship;
                                    privateField[row][column + 2] = ship;
                                }

                                if (privateField[row][column +2] == ship) {
                                    threeSquareShips++;

                                    if (row > 0 && column > 0) privateField[row -1][column -1] = nearShip;
                                    if (row > 0) {
                                                               privateField[row -1][column] = nearShip;
                                                               privateField[row -1][column +1] = nearShip;
                                                               privateField[row -1][column +2] = nearShip;
                                    }
                                    if (row > 0 && column < 4) privateField[row -1][column +3] = nearShip;
                                    if (column < 4)            privateField[row][column +3] = nearShip;
                                    if (row < 6 && column < 4) privateField[row +1][column +3] = nearShip;
                                    if (row < 6) {
                                                               privateField[row +1][column +2] = nearShip;
                                                               privateField[row +1][column +1] = nearShip;
                                                               privateField[row +1][column] = nearShip;
                                    }
                                    if (row < 6 && column > 0) privateField[row +1][column -1] = nearShip;
                                    if (column > 0)            privateField[row][column -1] = nearShip;
                                }
                            }
                        }

                        if (threeSquareShips == 1) break createThreeSquareShipLoop;

                        if (row < 5) {
                            if (privateField[row +1][column] == emptyCell && privateField[row +2][column] == emptyCell) {

                                boolean isSpawnShip = rand.nextInt(2) == ship;
                                if (isSpawnShip) {
                                    privateField[row +1][column] = ship;
                                    privateField[row +2][column] = ship;
                                }

                                if (privateField[row +2][column] == ship) {
                                    threeSquareShips++;

                                    if (row > 0 && column > 0) privateField[row -1][column -1] = nearShip;
                                    if (row > 0)               privateField[row -1][column] = nearShip;
                                    if (row > 0 && column < 6) privateField[row -1][column +1] = nearShip;
                                    if (column < 6) {
                                                               privateField[row][column +1] = nearShip;
                                                               privateField[row +1][column +1] = nearShip;
                                                               privateField[row +2][column +1] = nearShip;
                                    }
                                    if (row < 4 && column < 6) privateField[row +3][column +1] = nearShip;
                                    if (row < 4)               privateField[row +3][column] = nearShip;
                                    if (row < 4 && column > 0) privateField[row +3][column -1] = nearShip;
                                    if (column > 0) {
                                                               privateField[row +2][column -1] = nearShip;
                                                               privateField[row +1][column -1] = nearShip;
                                                               privateField[row][column -1] = nearShip;
                                    }
                                }
                            }
                        }

                        if (threeSquareShips == 1) break createThreeSquareShipLoop;

                        if (threeSquareShips < 1) {
                            privateField[row][column] = emptyCell;
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