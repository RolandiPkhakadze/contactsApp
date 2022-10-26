package com.example.contactsApp.Exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PhoneNotFoundException extends RuntimeException{

    public PhoneNotFoundException(String msg)
    {
        super(msg);
        log.error(msg);
    }
}
