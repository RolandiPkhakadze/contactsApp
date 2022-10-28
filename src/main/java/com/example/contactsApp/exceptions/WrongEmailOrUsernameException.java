package com.example.contactsApp.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WrongEmailOrUsernameException extends RuntimeException{

    public WrongEmailOrUsernameException(String msg) {
        super(msg);
        log.error(msg);
    }
}
