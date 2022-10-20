package com.example.contactsApp.Exception;

public class HistoryDoesNotExistException extends  RuntimeException{
    private String message;

    public HistoryDoesNotExistException(String msg)
    {
        super(msg);
        this.message = msg;
    }
}
