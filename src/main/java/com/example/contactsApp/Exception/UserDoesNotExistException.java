package com.example.contactsApp.Exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserDoesNotExistException extends RuntimeException{

    private String message;

    public UserDoesNotExistException(String msg)
    {
        super(msg);
        this.message = msg;
    }
}
