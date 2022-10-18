package com.example.contactsApp.service;


import com.example.contactsApp.entity.Contact;
import com.example.contactsApp.entity.User;
import com.example.contactsApp.repository.ContactRepository;
import com.example.contactsApp.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class ContactServiceImpl implements ContactService {
    private final UserRepository userRepository;
    private final ContactRepository contactRepository;

    @Override
    public void addContact(Contact contact, Long userId) {
        User userOptional = userRepository.getUserById(userId);
        log.debug("dsfdsfsd");

        contact.setUser(userOptional);
        contactRepository.save(contact);
    }

    @Override
    public void deleteContact(Long contactId) {
        contactRepository.deleteById(contactId);
    }
}
