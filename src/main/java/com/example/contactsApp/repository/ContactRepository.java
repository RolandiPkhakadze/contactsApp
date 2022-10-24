package com.example.contactsApp.repository;

import com.example.contactsApp.Exception.ContactDoesNotExistException;
import com.example.contactsApp.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository  extends JpaRepository<Contact, Long> {
    default Contact getContactById(String id) {
        return findContactById(id).orElseThrow(() -> new ContactDoesNotExistException("contact with id: "+id+" was not found."));
    }

    void deleteContactById(String id);

    Optional<Contact> findContactById(String id);
}
