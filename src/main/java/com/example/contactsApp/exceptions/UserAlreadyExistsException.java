package com.example.contactsApp.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException(String msg)
    {
        super(msg);
        log.error(msg);
    }
}
