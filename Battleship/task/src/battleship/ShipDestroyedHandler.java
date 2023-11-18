package battleship;

import battleship.entity.Coordinate;
import battleship.entity.Point;

public class ShipDestroyedHandler {
    public static final int X_COUNT = 17;
    private static int hitCounter = 0;
    public static boolean isAllShipsDestroyed(char [][] playerField) {
        incrementHitCounter(playerField);
        return X_COUNT == hitCounter;
    }

    public static void incrementHitCounter(char [][] playerField) {
        hitCounter = 0;
        for (char[] row : playerField) {
            for (char cell : row) {
                if (cell == 'X') {
                    hitCounter++;
                }
            }
        }
    }
    public static int registerHit(Point shot, Coordinate[] ships) {
        for (int i = 0; i < Main.SHIP_COUNT; i++) {
            if (isHitShip(shot, ships[i])) {
                ships[i].getHit();
                return i;
            }
        }
        return -1;
    }

    private static boolean isHitShip(Point shot, Coordinate ship) {
        if (ship.isVertical()) {
            if (shot.getNumber() == ship.getStart().getNumber()) {
                return (ship.getStart().getLetterIdx() >= shot.getLetterIdx()
                        || ship.getEnd().getLetterIdx() <= shot.getLetterIdx());
            }
            return false;
        } else {
            if (shot.getLetterIdx() == ship.getStart().getLetterIdx()) {
                return (ship.getStart().getNumber() >= shot.getNumber()
                        || ship.getEnd().getNumber() <= shot.getNumber());
            }
            return false;
        }
    }

}
