package com.example.contactsApp.Exception;

public class ContactDoesNotExistException extends  RuntimeException{
    private String message;

    public ContactDoesNotExistException(String msg)
    {
        super(msg);
        this.message = msg;
    }

}
