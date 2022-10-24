package com.example.contactsApp.Exception;

public class WrongEmailOrUsernameException extends RuntimeException{

    public WrongEmailOrUsernameException(String emailOrUsername) {
        super(String.format("user %s was not found.", emailOrUsername));
    }
}
