package com.example.contactsApp.repository;

import com.example.contactsApp.Exception.PhoneDoesNotExistException;
import com.example.contactsApp.entity.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhoneNumberRepository  extends JpaRepository<PhoneNumber, Long> {
    default PhoneNumber getPhoneById(Long id) {
        return findPhoneById(id).orElseThrow(() -> new PhoneDoesNotExistException("phone with id: "+id+" was not found."));
    }

    Optional<PhoneNumber> findPhoneById(Long id);
}
