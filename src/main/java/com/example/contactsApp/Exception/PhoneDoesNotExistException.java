package com.example.contactsApp.Exception;

public class PhoneDoesNotExistException extends RuntimeException{

    public PhoneDoesNotExistException(String msg)
    {
        super(String.format("phone with %s was not found.",msg));
    }
}
