package com.example.contactsApp.service;

import com.example.contactsApp.domain.Contact;


public interface ContactService {

    Contact addContact(Contact contact, Long userId);

    void deleteContact(String contactId);

    Contact updateContactPartially(Contact contact);

    Contact updateContact(Contact contact);


}
