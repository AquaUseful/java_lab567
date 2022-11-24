package org.psu.lab5.exception;

import javax.security.auth.message.AuthException;

public class LoginException extends AuthException {
    public LoginException(String message) {
        super(message);
    }
}
