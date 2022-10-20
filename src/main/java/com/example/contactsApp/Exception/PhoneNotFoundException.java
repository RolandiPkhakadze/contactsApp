package com.example.contactsApp.Exception;

public class PhoneNotFoundException extends RuntimeException{
    private String message;

    public PhoneNotFoundException(String msg)
    {
        super(msg);
        this.message = msg;
    }
}
