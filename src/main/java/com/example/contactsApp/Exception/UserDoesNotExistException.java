package com.example.contactsApp.Exception;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@Slf4j
public class UserDoesNotExistException extends RuntimeException{


    public UserDoesNotExistException(String msg)
    {
        super(msg);
        log.error(msg);
    }
}
