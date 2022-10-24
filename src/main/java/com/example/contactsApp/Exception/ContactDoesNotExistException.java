package com.example.contactsApp.Exception;

public class ContactDoesNotExistException extends  RuntimeException{

    public ContactDoesNotExistException(String phone)
    {
        super(String.format("contact with phone %s was not found.",phone));
    }

}
