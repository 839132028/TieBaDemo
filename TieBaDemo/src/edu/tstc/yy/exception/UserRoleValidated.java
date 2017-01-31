package edu.tstc.yy.exception;

/**
 * Created by w_2 on 2016-10-16.
 */
public class UserRoleValidated extends Exception {
    public UserRoleValidated(String message, Throwable cause) {
        super(message, cause);
    }

    public UserRoleValidated() {
        super();
    }

    public UserRoleValidated(String message) {
        super(message);
    }
}
