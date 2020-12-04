public class LocationException extends RuntimeException{

    public LocationException() {
        super("Incorrect location");
    }

    public LocationException(String message) {
        super(message);
    }
}
