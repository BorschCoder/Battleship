package battleship.enums;

public enum PlayerType {
    FIRST((byte) 1),
    SECOND((byte) 2),
    NONE((byte) 3);
    private final byte id;

    PlayerType(byte id) {
        this.id = id;
    }

   public byte getId() {
        return id;
    }
}

