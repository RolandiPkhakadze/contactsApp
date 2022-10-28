package com.example.contactsApp.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HistoryDoesNotExistException extends  RuntimeException{

    public HistoryDoesNotExistException(String msg)
    {
        super(String.format("history with id: %s was not found.",msg));
        log.error(msg);
    }
}
