package com.example.contactsApp.service;


import com.example.contactsApp.entity.Contact;
import com.example.contactsApp.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    private ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> GetContacts(){
        return  contactRepository.findAll();
    }

}
