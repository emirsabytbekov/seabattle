import java.util.Objects;
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


    static int threeSquareShipFirstSquareRow;
    static int threeSquareShipFirstSquareColumn;
    static int threeSquareShipSecondSquareRow;
    static int threeSquareShipSecondSquareColumn;
    static int threeSquareShipThirdSquareRow;
    static int threeSquareShipThirdSquareColumn;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        String[][] visibleField = new String[7][7];
        int[][] privateField = new int[7][7];

        String [][] designedPrivateField = new String[7][7];

        int oneSquareShips = 0;
        int twoSquareShips = 0;
        int threeSquareShips = 0;

        boolean isHitFirstSquareOfThreeSquareShip = false;
        boolean isHitSecondSquareOfThreeSquareShip = false;
        boolean isHitThirdSquareOfThreeSquareShip = false;


        randomlySpawnShips(rand, privateField, oneSquareShips, twoSquareShips, threeSquareShips);

        oneSquareShips = 4;
        twoSquareShips = 2;
        threeSquareShips = 1;


        fillDesignedPrivateField(privateField, designedPrivateField);

        fillVisibleField(visibleField);

        printVisibleField(visibleField);


        int guessRow = 0;
        int guessColumn = 0;

        int none = -1;
        int previousHitRow = none;
        int previousHitColumn = none;

        while(oneSquareShips > 0 || twoSquareShips > 0 || threeSquareShips > 0) {

            clearScreen();

            guessRow = sc.nextInt() - 1;
            guessColumn = sc.nextInt() - 1;

            if (guessRow == threeSquareShipFirstSquareRow && guessColumn == threeSquareShipFirstSquareColumn) {
                isHitFirstSquareOfThreeSquareShip = true;
            }
            else if (guessRow == threeSquareShipSecondSquareRow && guessColumn == threeSquareShipSecondSquareColumn) {
                isHitSecondSquareOfThreeSquareShip = true;
            }
            else if (guessRow == threeSquareShipThirdSquareRow && guessColumn == threeSquareShipThirdSquareColumn) {
                isHitThirdSquareOfThreeSquareShip = true;
            }

            if (!Objects.equals(visibleField[guessRow][guessColumn], hitEmoji) && !Objects.equals(visibleField[guessRow][guessColumn], sunkEmoji)) {
                visibleField[guessRow][guessColumn] = waterEmoji;
            }


            if (privateField[guessRow][guessColumn] == ship) {

                int sunk = 0;
                int hit = 1;
                int unknownYet = 2;
                int hitOrSunk = unknownYet;


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

                if (Objects.equals(visibleField[guessRow][guessColumn], hitEmoji) || Objects.equals(visibleField[guessRow][guessColumn], sunkEmoji)) {
                    privateField[guessRow][guessColumn] = emptyCell;
                }

                if (Objects.equals(visibleField[guessRow][guessColumn], hitEmoji)) {
                    previousHitRow = guessRow;
                    previousHitColumn = guessColumn;
                }
                else if (Objects.equals(visibleField[guessRow][guessColumn], sunkEmoji) && previousHitRow == -1) {
                    oneSquareShips--;
                }

                if (previousHitRow != none || previousHitColumn != none) {
                    if (previousHitRow > 0) {
                        hitOrSunk = privateField[previousHitRow - 1][previousHitColumn] == ship ? hit : unknownYet;
                    }
                    if (hitOrSunk == unknownYet && previousHitColumn < 6) {
                        hitOrSunk = privateField[previousHitRow][previousHitColumn + 1] == ship ? hit : unknownYet;
                    }
                    if (hitOrSunk == unknownYet && previousHitRow < 6) {
                        hitOrSunk = privateField[previousHitRow + 1][previousHitColumn] == ship ? hit : unknownYet;
                    }
                    if (hitOrSunk == unknownYet && previousHitColumn > 0) {
                        hitOrSunk = privateField[previousHitRow][previousHitColumn - 1] == ship ? hit : sunk;
                    }

                    visibleField[previousHitRow][previousHitColumn] = hitOrSunk == hit ? hitEmoji : sunkEmoji;

                    if (Objects.equals(visibleField[previousHitRow][previousHitColumn], hitEmoji)) {
                        privateField[previousHitRow][previousHitColumn] = emptyCell;
                        visibleField[guessRow][guessColumn] = hitEmoji;
                    }
                    else {
                        visibleField[previousHitRow][previousHitColumn] = sunkEmoji;
                        visibleField[guessRow][guessColumn] = sunkEmoji;

                        if ( !(guessRow == threeSquareShipFirstSquareRow && guessColumn == threeSquareShipFirstSquareColumn) && !(guessRow == threeSquareShipSecondSquareRow && guessColumn == threeSquareShipSecondSquareColumn) && !(guessRow == threeSquareShipThirdSquareRow && guessColumn == threeSquareShipThirdSquareColumn) ) twoSquareShips--;

                        previousHitRow = none;
                        previousHitColumn = none;
                    }

                    if (threeSquareShips > 0) {
                        if (isHitFirstSquareOfThreeSquareShip && isHitSecondSquareOfThreeSquareShip && isHitThirdSquareOfThreeSquareShip) {
                            privateField[threeSquareShipFirstSquareRow][threeSquareShipFirstSquareColumn] = emptyCell;
                            privateField[threeSquareShipSecondSquareRow][threeSquareShipSecondSquareColumn] = emptyCell;
                            privateField[threeSquareShipThirdSquareRow][threeSquareShipThirdSquareColumn] = emptyCell;

                            visibleField[threeSquareShipFirstSquareRow][threeSquareShipFirstSquareColumn] = sunkEmoji;
                            visibleField[threeSquareShipSecondSquareRow][threeSquareShipSecondSquareColumn] = sunkEmoji;
                            visibleField[threeSquareShipThirdSquareRow][threeSquareShipThirdSquareColumn] = sunkEmoji;

                            threeSquareShips--;

                            previousHitRow = none;
                            previousHitColumn = none;
                        }
                    }


                }

            }

            printVisibleField(visibleField);

        }
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

                                    threeSquareShipFirstSquareRow = row;
                                    threeSquareShipFirstSquareColumn = column;
                                    threeSquareShipSecondSquareRow = row;
                                    threeSquareShipSecondSquareColumn = column - 1;
                                    threeSquareShipThirdSquareRow = row;
                                    threeSquareShipThirdSquareColumn = column - 2;
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

                                    threeSquareShipFirstSquareRow = row;
                                    threeSquareShipFirstSquareColumn = column;
                                    threeSquareShipSecondSquareRow = row - 1;
                                    threeSquareShipSecondSquareColumn = column;
                                    threeSquareShipThirdSquareRow = row - 2;
                                    threeSquareShipThirdSquareColumn = column;
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

                                    threeSquareShipFirstSquareRow = row;
                                    threeSquareShipFirstSquareColumn = column;
                                    threeSquareShipSecondSquareRow = row;
                                    threeSquareShipSecondSquareColumn = column + 1;
                                    threeSquareShipThirdSquareRow = row;
                                    threeSquareShipThirdSquareColumn = column + 2;
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

                                    threeSquareShipFirstSquareRow = row;
                                    threeSquareShipFirstSquareColumn = column;
                                    threeSquareShipSecondSquareRow = row + 1;
                                    threeSquareShipSecondSquareColumn = column;
                                    threeSquareShipThirdSquareRow = row + 2;
                                    threeSquareShipThirdSquareColumn = column;
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

    public static void fillDesignedPrivateField (int[][] privateField, String[][] designedPrivateField) {
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
    }

    public static void fillVisibleField (String[][] visibleField) {
        for (int row = 0; row < 7; row++) {
            for (int column = 0; column < 7; column++) {
                visibleField[row][column] = fogEmoji;
            }
        }
    }

    public static void printVisibleField (String[][] visibleField) {
        for (int row = 0; row < 7; row++) {
            for (int column = 0; column < 7; column++) {
                System.out.print(visibleField[row][column]);
            }
            System.out.println();
        }
    }

    public static void clearScreen () {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


}