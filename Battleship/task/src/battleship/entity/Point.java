package battleship.entity;

import java.util.Arrays;

public class Point {
    private static final int LETTER_COORDINATE = 0;
    private static final int NUMBER_COORDINATE = 1;

    private int[] pointCords;

    public Point() {
    }

    public Point(int idxLetter, int number) {
        this.pointCords = new int[2];
        this.pointCords[LETTER_COORDINATE] = idxLetter;
        this.pointCords[NUMBER_COORDINATE] = number;
    }

    public Point(int[] pointCords) {
        this.pointCords = pointCords;
    }

    private int[] getPointCords(){
       return pointCords;
    }

    public int getLetterIdx() {
        return pointCords[LETTER_COORDINATE];
    }

    public int getNumber() {
        return pointCords[NUMBER_COORDINATE];
    }

    public void setLetterIdx(int letterIdx) {
        this.pointCords[LETTER_COORDINATE] = letterIdx;
    }

    public void setNumber(int number) {
        this.pointCords[NUMBER_COORDINATE] = number;
    }


    @Override
    public String toString() {
        return "Point{" +
                "pointCords=" + Arrays.toString(pointCords) +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if (this == o)  return true;
        if (o == null || o.getClass() != this.getClass() ) return false;

        Point that = (Point) o;
        return Arrays.equals(this.pointCords , that.getPointCords());
    }

}
