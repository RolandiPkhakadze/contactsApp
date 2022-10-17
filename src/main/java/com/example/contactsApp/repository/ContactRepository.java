package com.example.contactsApp.repository;

import com.example.contactsApp.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository  extends JpaRepository<Contact, Long> {
}