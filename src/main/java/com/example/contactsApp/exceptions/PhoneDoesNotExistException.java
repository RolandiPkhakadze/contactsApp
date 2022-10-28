package com.example.contactsApp.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PhoneDoesNotExistException extends RuntimeException{

    public PhoneDoesNotExistException(String msg)
    {
        super(msg);
        log.error(msg);
    }
}
