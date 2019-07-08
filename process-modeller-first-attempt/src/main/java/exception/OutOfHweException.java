package exception;

import java.io.IOException;

public class OutOfHweException extends IOException {

    public OutOfHweException(String message) {
        super(message);
    }

    public OutOfHweException(String message, Throwable cause) {
        super(message, cause);
    }

    public OutOfHweException(Throwable cause) {
        super(cause);
    }
}
