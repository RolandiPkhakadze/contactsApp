package com.example.contactsApp.Exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CallTimesException extends RuntimeException {
    private String message;

    public CallTimesException(String msg)
    {
        super(msg);
        this.message = msg;
    }
}
