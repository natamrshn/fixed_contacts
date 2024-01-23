package spring.boot.optic.okulist.exception;

public class VerificationCodeMismatchException extends RuntimeException {
    public VerificationCodeMismatchException(String message) {
        super(message);
    }
}
