package com.example.contactsApp.Exception;

public class HistoryDoesNotExistException extends  RuntimeException{


    public HistoryDoesNotExistException(Long id)
    {
        super(String.format("history with id: %d was not found.",id));
    }
}
