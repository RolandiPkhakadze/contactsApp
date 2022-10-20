package com.example.contactsApp.Exception;

public class ProviderDoesNotExistException extends RuntimeException{
    private String message;

    public ProviderDoesNotExistException(String msg)
    {
        super(msg);
        this.message = msg;
    }
}
