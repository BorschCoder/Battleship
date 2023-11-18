package battleship;

import battleship.entity.Point;
public class ShootingValidator {



    public static boolean isCorrectPoint(Point shot){

        int letterIdx = shot.getLetterIdx();
        int number = shot.getNumber();

        return !((letterIdx < 0 | letterIdx >9) | (number < 0 | number >9));
    }

    public static boolean isHit(Point shot, char[][] playerField){
        return playerField[shot.getLetterIdx()][shot.getNumber()] == 'O' || playerField[shot.getLetterIdx()][shot.getNumber()] == 'X';
    }
}
