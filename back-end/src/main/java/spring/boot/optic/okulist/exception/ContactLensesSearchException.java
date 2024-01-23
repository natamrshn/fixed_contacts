package spring.boot.optic.okulist.exception;

public class ContactLensesSearchException extends RuntimeException {
    public ContactLensesSearchException() {
    }

    public ContactLensesSearchException(String message) {
        super(message);
    }

    public ContactLensesSearchException(String message, Throwable cause) {
        super(message, cause);
    }
}
