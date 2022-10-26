package com.example.contactsApp.Exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProviderDoesNotExistException extends RuntimeException{


    public ProviderDoesNotExistException(String msg)
    {
        super(msg);
        log.error(msg);
    }
}
