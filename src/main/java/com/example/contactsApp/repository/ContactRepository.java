package com.example.contactsApp.repository;

import com.example.contactsApp.exceptions.ContactDoesNotExistException;
import com.example.contactsApp.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    default Contact getContactById(String id) {
        return findContactById(id).orElseThrow(() -> new ContactDoesNotExistException(String.format("contact with phone %s was not found.",id)));
    }


    void deleteContactById(String id);

    Optional<Contact> findContactById(String id);
}
