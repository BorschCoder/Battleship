package battleship.entity;

public class Coordinate {

    private Point start;
    private Point end;

    private int health;

    private boolean isVertical;

    public void getHit() {
        this.health -= 1;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isDead() {
        return health == 0;
    }

    public Coordinate() {

    }

    public Coordinate(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public boolean isVertical() {
        return isVertical;
    }

    public void setVertical(boolean vertical) {
        isVertical = vertical;
    }

    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Coordinate that = (Coordinate) o;
        if (!this.start.equals(that.getStart())) return false;
        return this.end.equals(that.getEnd());
    }

}
