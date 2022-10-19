package com.example.contactsApp.service;

import com.example.contactsApp.entity.Contact;


public interface ContactService {

    void addContact(Contact contact, Long userId);

    void deleteContact(Long contactId);
}
