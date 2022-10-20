package com.example.contactsApp.service.contactServices;

import com.example.contactsApp.entity.Contact;


public interface ContactService {

    Contact addContact(Contact contact, Long userId);

    void deleteContact(Long contactId);

    Contact updateContactPartially(Contact contact, Long id);

    Contact updateContact(Contact contact, Long id);
}
