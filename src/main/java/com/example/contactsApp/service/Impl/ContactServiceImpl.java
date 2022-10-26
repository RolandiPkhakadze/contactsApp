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
        log.debug(String.format("user with id: %d was needed and returned successfully",userId));

        contact.setUser(userOptional);
        contact = contactRepository.save(contact);

        log.debug(String.format("contact was successfully created with phone %s",contact.getId()));
        return contact;
    }

    @Transactional
    @Override
    public void deleteContact(String contactId) {
        contactRepository.getContactById(contactId);
        contactRepository.deleteContactById(contactId);
        log.debug(String.format("Contact with phone: %s was successfully deleted",contactId));
    }
    
    @Transactional(rollbackFor = ContactDoesNotExistException.class)
    @Override
    public Contact updateContactPartially(Contact contact) {
        Contact contactForSave = contactRepository.getContactById(contact.getId());
        log.debug("Requested to get contact with phone number"+contactForSave.getId());
        contact.setUser(contactForSave.getUser());
        contact = contactRepository.save(mapper.contactNullExclude(contactForSave,contact));
        log.debug(String.format("contact with phone %s was successfully updated",contact.getPhoneNumber()));
        return contact;
    }

    @Transactional(rollbackFor = ContactDoesNotExistException.class)
    @Override
    public Contact updateContact(Contact contact) {
        Contact contactFromBase = contactRepository.getContactById(contact.getId());
        log.debug("Requested to get contact with phone number"+contactFromBase.getId());
        contact.setUser(contactFromBase.getUser());
        contact = contactRepository.save(contact);

        log.debug(String.format("contact with phone %s was successfully updated",contact.getPhoneNumber()));
        return contact;
    }
}
