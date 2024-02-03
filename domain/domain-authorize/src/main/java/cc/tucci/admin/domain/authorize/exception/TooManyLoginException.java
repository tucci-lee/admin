package cc.tucci.admin.domain.authorize.exception;

/**
 * @author tucci
 */
public class TooManyLoginException extends RuntimeException {

    public TooManyLoginException() {
    }

    public TooManyLoginException(String message) {
        super(message);
    }

    public TooManyLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public TooManyLoginException(Throwable cause) {
        super(cause);
    }

    public TooManyLoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
