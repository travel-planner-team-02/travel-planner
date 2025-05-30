package com.laioffer.travelplannerbe.authentication;

public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException() {
        super("User already exist");
    }
}
