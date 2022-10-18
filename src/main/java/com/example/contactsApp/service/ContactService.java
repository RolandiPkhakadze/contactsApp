package com.example.contactsApp.service;


import com.example.contactsApp.entity.Contact;
import com.example.contactsApp.entity.User;
import com.example.contactsApp.repository.ContactRepository;
import com.example.contactsApp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    private ContactRepository contactRepository;
    private UserRepository userRepository;

    public ContactService(ContactRepository contactRepository, UserRepository userRepository) {
        this.contactRepository = contactRepository;
        this.userRepository = userRepository;
    }

    public List<Contact> GetContacts(){
        return  contactRepository.findAll();
    }

    public void addContact(Contact contact, Long userId) {
        Optional<User> userOptional = this.userRepository.getUserById(userId);

        if(!userOptional.isPresent()){
            System.out.println(userId);
            throw new IllegalStateException();
        }

        contact.setUser(userOptional.get());
        contactRepository.save(contact);
    }


}
