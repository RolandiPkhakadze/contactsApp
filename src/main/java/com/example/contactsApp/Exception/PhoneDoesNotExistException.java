package com.example.contactsApp.Exception;

public class PhoneDoesNotExistException extends RuntimeException{
    private String message;

    public PhoneDoesNotExistException(String msg)
    {
        super(msg);
        this.message = msg;
    }
}
