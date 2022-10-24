package com.example.contactsApp.Exception;

public class ProviderDoesNotExistException extends RuntimeException{


    public ProviderDoesNotExistException(Long id)
    {
        super(String.format("provider with id: %d was not found.",id));
    }
}
