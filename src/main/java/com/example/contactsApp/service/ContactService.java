package com.example.contactsApp.service;

import com.example.contactsApp.entity.Contact;

import java.util.List;

public interface ContactService {

    List<Contact> getContacts();

    void addContact(Contact contact, Long userId);
}
