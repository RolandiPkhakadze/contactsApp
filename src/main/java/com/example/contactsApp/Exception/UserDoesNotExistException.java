package com.example.contactsApp.Exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserDoesNotExistException extends RuntimeException{


    public UserDoesNotExistException(Long id)
    {
            super(String.format("user with id: %d was not found.", id));
    }
}
