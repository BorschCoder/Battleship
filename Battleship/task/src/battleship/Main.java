package battleship;

import battleship.entity.Coordinate;
import battleship.entity.Point;
import battleship.enums.ErrorType;
import battleship.enums.PlayerType;

import java.util.Scanner;

public class Main {
    public static final int FIELD_LENGHT = 10;
    public static final int SHIP_COUNT = 5;
    private static PlayerType playerTypeOnline = PlayerType.FIRST;
    private static Scanner scanner = new Scanner(System.in);
    private static char[][] player_First_Field = new char[FIELD_LENGHT][FIELD_LENGHT];
    private static char[][] player_Second_Field = new char[FIELD_LENGHT][FIELD_LENGHT];

    private static Coordinate[] shipsPlayer1 = new Coordinate[SHIP_COUNT];
    private static Coordinate[] shipsPlayer2 = new Coordinate[SHIP_COUNT];
    private static boolean gameIsOnline = true;

    public static boolean isGameIsOnline() {
        return gameIsOnline;
    }

    public static void setGameIsOnline(boolean gameIsOnline) {
        Main.gameIsOnline = gameIsOnline;
    }

    public static void main(String[] args) {

        setupGame();
        processingGame();

    }

    private static void processingGame() {

        while (Main.gameIsOnline) {
            char[][] enemyField = getEnemyField();
            char[][] playerFieldCurrentPlayer = getCurrentPlayerField();
            View.showEmptyField();

            View.showDividingStrip();

            View.showField(playerFieldCurrentPlayer);
            Coordinate[] ships = getCurrentShips();
            View.showTurnMessage();
            Point shot = enterPoint();


            if (!ShootingValidator.isCorrectPoint(shot)) {
                View.showErrorMessage(ErrorType.WRONG_POINT_SHOT, 0);
            } else {
                boolean hit = ShootingValidator.isHit(shot, enemyField);
                putTheHitMark(shot, hit);

                int shipIdx = 0;
                if (hit) {
                    shipIdx = ShipDestroyedHandler.registerHit(shot, ships);
                }

                boolean isSanked = shipIdx == -1 ? false : ships[shipIdx].isDead();

                if (isSanked & !ShipDestroyedHandler.isAllShipsDestroyed(enemyField)) {
                    View.showSunkShipMessage();
                } else {
                    if (!ShipDestroyedHandler.isAllShipsDestroyed(enemyField)) {
                        View.showShotResultMessage(hit);
                    } else {
                        Main.setGameIsOnline(false);
                        break;
                    }
                }

            }
            switchPlayer();
        }
        View.showWonMessage();
    }


    private static void setupGame() {
        int countPlayersPlacedShips = 0;

        while (countPlayersPlacedShips < 2) {
            countPlayersPlacedShips = countPlayersPlacedShips + 1;

            View.showPlaceShipsMessage();
            char[][] playerField = getCurrentPlayerField();
            Coordinate[] ships = getCurrentShips();

            View.initializeField(playerField);
            View.showField(playerField);

            for (int i = 0; i < SHIP_COUNT; i++) {

                View.showMessageShip(i);
                Coordinate coordinate = null;

                boolean isCoordinateInvalid = true;
                while (isCoordinateInvalid) {

                    coordinate = enterCoordinate();
                    ErrorType result = processCoordinate(coordinate, i);

                    if (result != ErrorType.NONE) {
                        View.showErrorMessage(result, i);
                    } else {
                        isCoordinateInvalid = false;
                        ships[i] = coordinate;
                    }
                }
                arrangeShip(coordinate);
                View.showField(playerField);

            }

            switchPlayer();
        }

    }

    private static ErrorType processCoordinate(Coordinate coordinate, int idx) {
        char[][] playerField = getCurrentPlayerField();
        if (!CoordinateValidator.isOrderCorrect(coordinate)) swapPoint(coordinate);
        if (CoordinateValidator.isOutOfBounds(coordinate)) return ErrorType.OUT_OF_BOUNDS;
        if (!CoordinateValidator.isCordsOnLine(coordinate)) return ErrorType.WRONG_LOCATION;
        if (!CoordinateValidator.validateShipLength(coordinate, idx)) return ErrorType.WRONG_LENGTH;
        if (CoordinateValidator.isShipCross(coordinate, playerField)) return ErrorType.CROSS_SHIPS;
        if (CoordinateValidator.isTooClose(coordinate, playerField)) return ErrorType.TOO_CLOSE;


        return ErrorType.NONE;
    }
    public static void swapPoint(Coordinate coordinate) {
        Point temp = coordinate.getStart();
        coordinate.setStart(coordinate.getEnd());
        coordinate.setEnd(temp);
    }

    private static Coordinate enterCoordinate() {
        String inputCoordinate = scanner.nextLine();
        inputCoordinate = inputCoordinate.trim();
        String[] coords = inputCoordinate.split(" ");

        Coordinate coordinate = new Coordinate();
        coordinate.setStart(new Point(returnIndexOfLetter(coords[0].charAt(0))
                , Integer.parseInt(coords[0].substring(1), 10) - 1));
        coordinate.setEnd(new Point(returnIndexOfLetter(coords[1].charAt(0))
                , Integer.parseInt(coords[1].substring(1), 10) - 1));

        return coordinate;
    }

    private static Point enterPoint() {
        String inputCoordinate = scanner.nextLine().trim();
        return new Point(returnIndexOfLetter(inputCoordinate.charAt(0))
                , Integer.parseInt(inputCoordinate.substring(1), 10) - 1);
    }

    private static void arrangeShip(Coordinate coordinate) {

        if (CoordinateValidator.isVerticalShip(coordinate)) {
            coordinate.setVertical(true);
            arrangeVerticalShip(coordinate);
        } else {
            coordinate.setVertical(false);
            arrangeHorizontalShip(coordinate);
        }
    }


    private static void arrangeVerticalShip(Coordinate coordinate) {
        char[][] playerField = getCurrentPlayerField();
        int endLoop = coordinate.getEnd().getLetterIdx();
        int column = coordinate.getStart().getNumber();

        int shipLength = 0;
        for (int i = coordinate.getStart().getLetterIdx(); i <= endLoop; i++) {
            playerField[i][column] = 'O';
            shipLength += 1;
        }
        coordinate.setHealth(shipLength);
    }

    private static void arrangeHorizontalShip(Coordinate coordinate) {
        char[][] playerField = getCurrentPlayerField();
        int endLoop = coordinate.getEnd().getNumber();
        int row = coordinate.getStart().getLetterIdx();

        int shipLength = 0;
        for (int i = coordinate.getStart().getNumber(); i <= endLoop; i++) {
            playerField[row][i] = 'O';
            shipLength += 1;
        }
        coordinate.setHealth(shipLength);
    }


    private static void putTheHitMark(Point shot, boolean hit) {
        char[][] playerField = getEnemyField();
        playerField[shot.getLetterIdx()][shot.getNumber()] = hit == true ? 'X' : 'M';
    }

    private static int returnIndexOfLetter(char point) {
        return (point - 65);
    }

    private static char[][] getCurrentPlayerField() {
        return playerTypeOnline == PlayerType.FIRST ? getPlayer_First_Field() : getPlayer_Second_Field();
    }

    private static char[][] getEnemyField() {
        return playerTypeOnline == PlayerType.SECOND ? getPlayer_First_Field() : getPlayer_Second_Field();
    }

    public static char[][] getPlayer_First_Field() {
        return player_First_Field;
    }

    private static char[][] getPlayer_Second_Field() {
        return player_Second_Field;
    }

    public static PlayerType getPlayerTypeOnline() {
        return playerTypeOnline;
    }

    private static void setPlayerTypeOnline(PlayerType playerTypeOnline) {
        Main.playerTypeOnline = playerTypeOnline;
    }


    public static void switchPlayer() {
        System.out.println("Press Enter and pass the move to another player");
        System.out.println("...");

        scanner.nextLine();

        setPlayerTypeOnline(playerTypeOnline == PlayerType.FIRST ? PlayerType.SECOND : PlayerType.FIRST);

    }

    private static Coordinate[] getCurrentShips() {
        return playerTypeOnline == PlayerType.SECOND ? shipsPlayer2 : shipsPlayer1;
    }
}
