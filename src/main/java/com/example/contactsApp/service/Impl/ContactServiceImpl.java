package com.example.contactsApp.service.Impl;


import com.example.contactsApp.entity.Contact;
import com.example.contactsApp.entity.User;
import com.example.contactsApp.repository.ContactRepository;
import com.example.contactsApp.repository.UserRepository;
import com.example.contactsApp.service.Intf.ContactService;
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


    @Transactional
    @Override
    public Contact addContact(Contact contact, Long userId) {
        User userOptional = userRepository.getUserById(userId);
        log.debug("dsfdsfsd");

        contact.setUser(userOptional);
        return contactRepository.save(contact);
    }

    @Transactional
    @Override
    public void deleteContact(Long contactId) {
        contactRepository.deleteById(contactId);
    }
    
    @Transactional
    @Override
    public Contact updateContactPartially(Contact contact, Long id) {
        Contact contactForSave = contactRepository.getContactById(id);
        return contactRepository.save(CustomMapperImpl.contactNullExclude(contactForSave,contact));
    }

    @Transactional
    @Override
    public Contact updateContact(Contact contact, Long id) {
        contactRepository.getContactById(id);
        contact.setId(id);
        return contactRepository.save(contact);
    }
}
