package battleship.enums;

public enum Ship {
    AIRCRAFT_CARRIER("Aircraft Carrier",5),
    BATTLESHIP("Battleship",4),
    SUBMARINE("Submarine",3),
    CRUISER("Cruiser",3),
    DESTROYER("Destroyer",2);

    Ship(String name, int cells) {
        this.name = name;
        this.cell = cells;
    }

    private String name;
    private int cell;

    public String getName() {
        return name;
    }

    public int getCell() {
        return cell;
    }

}
