package battleship.enums;

public enum ErrorType {

    TOO_CLOSE("You placed it too close to another one."),
    WRONG_LOCATION("Wrong ship location!"),
    WRONG_LENGTH("Wrong length of the %s!"),


    OUT_OF_BOUNDS("Coordinate is out of your primary grid!"),
    CROSS_SHIPS("You placed it crossed another ship!"),
    WRONG_POINT_SHOT("You entered the wrong coordinates!"),
    NONE("");
    ErrorType(String message){
       this.message = message;
    }

    private final String message;

    public String getMessage() {
        return message;
    }
}
