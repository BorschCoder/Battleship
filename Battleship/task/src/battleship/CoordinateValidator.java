package battleship;

import battleship.entity.Coordinate;
import battleship.enums.Ship;

public class CoordinateValidator {


    public static boolean isOrderCorrect(Coordinate coordinate) {

        if (isVerticalShip(coordinate)) {
            return coordinate.getStart().getLetterIdx() - coordinate.getEnd().getLetterIdx() < 0;
        } else {
            return coordinate.getStart().getNumber() - coordinate.getEnd().getNumber() < 0;

        }

    }

    public static boolean isShipCross(Coordinate coordinate, char[][] playerField) {
        boolean isVertical = isVerticalShip(coordinate);
        int endLoop = isVertical ? coordinate.getEnd().getLetterIdx() : coordinate.getEnd().getNumber();
        int i = isVertical ? coordinate.getStart().getLetterIdx() : coordinate.getStart().getNumber();

        int idx = isVertical ? coordinate.getStart().getNumber() : coordinate.getStart().getLetterIdx();

        for (; i <= endLoop; i++) {
            if (isVertical) {
                if (playerField[i][idx] == '0')
                    return true;
            } else {
                if (playerField[idx][i] == '0') {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isTooClose(Coordinate coordinate, char[][] playerField) {
        int y1 = coordinate.getStart().getLetterIdx();
        int y2 = coordinate.getEnd().getLetterIdx();

        int x1 = coordinate.getStart().getNumber();
        int x2 = coordinate.getEnd().getNumber();

        y1 = isOnUpperEdge(coordinate) ? y1 : (y1 - 1);
        y2 = isOnBottomEdge(coordinate) ? y2 : (y2 + 1);
        x1 = isOnLeftEdge(coordinate) ? x1 : (x1 - 1);
        x2 = isOnRightEdge(coordinate) ? x2 : (x2 + 1);

        for (int y = y1; y <= y2; y++) {
            for (int x = x1; x <= x2; x++) {
                if (playerField[y][x] == 'O') {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean isOnUpperEdge(Coordinate coordinate){

        return coordinate.getStart().getLetterIdx() == 0;

    }
    private static boolean isOnBottomEdge(Coordinate coordinate){
        return coordinate.getEnd().getLetterIdx() == 9;

    }
    private static boolean isOnLeftEdge(Coordinate coordinate){
        return coordinate.getStart().getNumber()== 0;

    }
    private static boolean isOnRightEdge(Coordinate coordinate){
        return coordinate.getEnd().getNumber() ==  9;

    }


    public static boolean isOutOfBounds(Coordinate coordinate) {
        int idxStartField = 0;

        int startLetterIdx = coordinate.getStart().getLetterIdx();
        int endLetterIdx = coordinate.getEnd().getLetterIdx();
        int startNumber = coordinate.getStart().getNumber();
        int endNumber = coordinate.getEnd().getNumber();

        if (startLetterIdx > Main.FIELD_LENGHT || startLetterIdx < idxStartField) return true;
        if (endLetterIdx > Main.FIELD_LENGHT || endLetterIdx < idxStartField) return true;
        if (startNumber > Main.FIELD_LENGHT || startNumber < idxStartField) return true;
        if (endNumber > Main.FIELD_LENGHT || endNumber < idxStartField) return true;

        return false;
    }


    public static boolean isVerticalShip(Coordinate coordinate) {
        return coordinate.getStart().getNumber() == coordinate.getEnd().getNumber();
    }

    public static boolean isCordsOnLine(Coordinate coordinate) {
        if (coordinate.getStart().getLetterIdx() != coordinate.getEnd().getLetterIdx())
            return coordinate.getStart().getNumber() == coordinate.getEnd().getNumber();
        return true;
    }

    public static boolean validateShipLength(Coordinate coordinate, int idx) {
        int length = isVerticalShip(coordinate)
                ? Math.abs(coordinate.getStart().getLetterIdx() - coordinate.getEnd().getLetterIdx())
                : Math.abs(coordinate.getStart().getNumber() - coordinate.getEnd().getNumber());
        length++;
        return length == Ship.values()[idx].getCell();
    }



}
