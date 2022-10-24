package com.example.contactsApp.service.Impl;


import com.example.contactsApp.Exception.ContactDoesNotExistException;
import com.example.contactsApp.entity.Contact;
import com.example.contactsApp.entity.User;
import com.example.contactsApp.repository.ContactRepository;
import com.example.contactsApp.repository.UserRepository;
import com.example.contactsApp.service.ContactService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class ContactServiceImpl implements ContactService {
    private final UserRepository userRepository;
    private final ContactRepository contactRepository;
    private final CustomMapperImpl mapper;


    @Transactional(rollbackFor = ContactDoesNotExistException.class)
    @Override
    public Contact addContact(Contact contact, Long userId) {
        User userOptional = userRepository.getUserById(userId);
        log.debug("dsfdsfsd");

        contact.setUser(userOptional);
        return contactRepository.save(contact);
    }

    @Transactional
    @Override
    public void deleteContact(String contactId) {
        contactRepository.deleteContactById(contactId);
    }
    
    @Transactional(rollbackFor = ContactDoesNotExistException.class)
    @Override
    public Contact updateContactPartially(Contact contact) {
        Contact contactForSave = contactRepository.getContactById(contact.getId());
        contact.setUser(contactForSave.getUser());
        return contactRepository.save(mapper.contactNullExclude(contactForSave,contact));
    }

    @Transactional(rollbackFor = ContactDoesNotExistException.class)
    @Override
    public Contact updateContact(Contact contact) {
        Contact contactFromBase = contactRepository.getContactById(contact.getId());
        contact.setUser(contactFromBase.getUser());
        return contactRepository.save(contact);
    }
}
