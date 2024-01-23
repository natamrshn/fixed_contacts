package spring.boot.optic.okulist.exception;

public class InvalidOrderRequestException extends RuntimeException {
    public InvalidOrderRequestException(String message) {
        super(message);
    }
}
