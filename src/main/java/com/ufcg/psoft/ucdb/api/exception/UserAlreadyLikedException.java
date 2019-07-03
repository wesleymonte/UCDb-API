package com.ufcg.psoft.ucdb.api.exception;

public class UserAlreadyLikedException extends RuntimeException {

    public UserAlreadyLikedException(String message) {
        super(message);
    }
}
