package carsharing.db;

public class SqlRuntimeException extends RuntimeException {
    public SqlRuntimeException() {
    }

    public SqlRuntimeException(String message) {
        super(message);
    }

    public SqlRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public SqlRuntimeException(Throwable cause) {
        super(cause);
    }

    public SqlRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
