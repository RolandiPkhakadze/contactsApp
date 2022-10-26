package com.example.contactsApp.Exception;


public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException(String msg)
    {
        super(String.format("user %s already exists.",msg));
    }
}
