package battleship;

import battleship.enums.ErrorType;
import battleship.enums.Ship;

public class View {
    private static char[] letterPoint = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};

    private static final String ERROR_TEMPLATE = "Error! %s Try again:\n";

    private static final String SHIP_DESCRIPTION = "%s (%d cells)";

    public static boolean isHideField() {
        return hideField;
    }

    public static void setHideField(boolean hideField) {
        View.hideField = hideField;
    }

    private static boolean hideField = false;

    public static void initializeField(char[][] playerField) {

        for (int i = 0; i < playerField.length; i++) {
            for (int j = 0; j < playerField.length; j++) {
                if (i < playerField.length & j < playerField.length) playerField[i][j] = '~';
            }
        }

    }

    public static void showEmptyField() {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < Main.FIELD_LENGHT; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(letterPoint[i]);
            for (int j = 0; j < Main.FIELD_LENGHT; j++) {
                sb.append(" " + '~');
            }
            String string = sb.toString();

            System.out.println(string);

        }

    }

    public static void showField(char[][] playerField) {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        boolean hideField = View.isHideField();
        for (int i = 0; i < playerField.length; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(letterPoint[i]);
            for (int j = 0; j < playerField.length; j++) {
                sb.append(" " + (hideField & playerField[i][j] == 'O' ? '~' : playerField[i][j]));
            }
            String string = sb.toString();

            System.out.println(string);

        }
    }
    public static void showDividingStrip() {
        System.out.println("---------------------");
    }

    public static void showMessageShip(int shipIndex) {
        System.out.printf("Enter the coordinates of the %s:\n",
                obtainShipDescription(Ship.values()[shipIndex]));
    }

    public static void showErrorMessage(ErrorType result, int shipIndex) {
        String message = result.getMessage();
        if (result == ErrorType.WRONG_LENGTH) {
            message = String.format(result.getMessage(), Ship.values()[shipIndex].getName());
        }
        System.out.printf(ERROR_TEMPLATE, message);
    }

    private static String obtainShipDescription(Ship ship) {
        return String.format(SHIP_DESCRIPTION, ship.getName(), ship.getCell());
    }

    public static void showShotResultMessage(boolean hit) {
        if (!hit) {
            System.out.println("You missed!");
        } else {
            System.out.println("You hit a ship!");
        }
    }

    public static void showSunkShipMessage() {
        System.out.println("You sank a ship!");
    }

    public static void showWonMessage() {
        System.out.println("You sank the last ship. You won. Congratulations!");
    }

    public static void showPlaceShipsMessage() {
        System.out.printf("Player %s, place your ships on the game field\n", Main.getPlayerTypeOnline().getId());
    }

    public static void showTurnMessage() {
        System.out.printf("Player %s, it's your turn:\n", Main.getPlayerTypeOnline().getId());
    }
}
