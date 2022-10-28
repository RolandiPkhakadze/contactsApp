package com.example.contactsApp.exceptions;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@Slf4j
public class WrongPasswordException extends RuntimeException{

    public WrongPasswordException(String msg)
    {
        super(msg);
        log.error(msg);
    }
}
