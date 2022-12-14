package com.example.contactsApp.Exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContactDoesNotExistException extends  RuntimeException{

    public ContactDoesNotExistException(String msg)
    {
        super(msg);
        log.error(msg);
    }

}
