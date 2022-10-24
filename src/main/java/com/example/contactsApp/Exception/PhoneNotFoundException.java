package com.example.contactsApp.Exception;

public class PhoneNotFoundException extends RuntimeException{

    public PhoneNotFoundException(String id)
    {
        super(String.format("phone with %s was not found.",id));
    }
}
