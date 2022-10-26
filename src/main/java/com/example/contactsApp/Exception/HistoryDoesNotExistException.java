package com.example.contactsApp.Exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HistoryDoesNotExistException extends  RuntimeException{


    public HistoryDoesNotExistException(String msg)
    {
        super(String.format("history with id: %d was not found.",msg));
        log.error(msg);
    }
}
