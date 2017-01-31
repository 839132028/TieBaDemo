package edu.tstc.yy.exception;

/**
 * Created by w_2 on 2016-10-19.
 */
public class NotAuthorException extends Exception {
    public NotAuthorException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotAuthorException() {
        super();
    }

    public NotAuthorException(String message) {
        super(message);
    }
}
