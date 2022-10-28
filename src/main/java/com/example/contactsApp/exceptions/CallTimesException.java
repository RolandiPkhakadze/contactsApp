package com.example.contactsApp.exceptions;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@NoArgsConstructor
@Slf4j
public class CallTimesException extends RuntimeException {

    public CallTimesException(String msg)
    {
        super(msg);
        log.error(msg);
    }
}
