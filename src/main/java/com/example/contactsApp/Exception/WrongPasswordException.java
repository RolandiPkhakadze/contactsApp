package com.example.contactsApp.Exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor

public class WrongPasswordException extends RuntimeException{
    private String message;

    public WrongPasswordException(String msg)
    {
        super(msg);
        this.message = msg;
    }
}
