package com.example.contactsApp.service.contactServices;


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
    public Contact addContact(Contact contact, Long userId) {
        User userOptional = userRepository.getUserById(userId);
        log.debug("dsfdsfsd");

        contact.setUser(userOptional);
        return contactRepository.save(contact);
    }

    @Override
    public void deleteContact(Long contactId) {
        contactRepository.deleteById(contactId);
    }

    @Override
    public Contact updateContactPartially(Contact contact, Long id) {
        Contact contactForSave = contactRepository.getContactById(id);
        var firstName = contact.getFirstName();
        contactForSave.setFirstName(firstName != null ? firstName : contactForSave.getFirstName());
        var lastName = contact.getLastName();
        contactForSave.setLastName(lastName != null ? lastName : contactForSave.getLastName());
        var isFavorite = contact.getIsFavorite();
        contactForSave.setIsFavorite(isFavorite != null ? isFavorite : contactForSave.getIsFavorite());
        return updateContact(contactForSave, id);
    }

    @Override
    public Contact updateContact(Contact contact, Long id) {
        contactRepository.getContactById(id);
        contact.setId(id);
        return contactRepository.save(contact);
    }
}
