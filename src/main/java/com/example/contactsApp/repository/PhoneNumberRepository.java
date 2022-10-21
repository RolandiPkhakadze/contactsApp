package com.example.contactsApp.repository;

import com.example.contactsApp.Exception.PhoneDoesNotExistException;
import com.example.contactsApp.Exception.PhoneNotFoundException;
import com.example.contactsApp.entity.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhoneNumberRepository  extends JpaRepository<PhoneNumber, Long> {
    default PhoneNumber getPhoneById(String id) {
        return findPhoneNumberByPhoneNumber(id).orElseThrow(() -> new PhoneDoesNotExistException("phone with id: "+id+" was not found."));
    }

    default PhoneNumber getPhoneNumberByPhoneNumber(String phoneNumber) {
        return findPhoneNumberByPhoneNumber(phoneNumber).orElseThrow(() -> new PhoneNotFoundException(String.format("phone with %S phone number not found", phoneNumber)));
    }

    @Query("SELECT u FROM PhoneNumber u WHERE u.phoneNumber = ?1 ")
    Optional<PhoneNumber> findPhoneNumberByPhoneNumber(String phoneNumber);
}
