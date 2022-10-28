package com.example.contactsApp.repository;

import com.example.contactsApp.exceptions.PhoneDoesNotExistException;
import com.example.contactsApp.exceptions.PhoneNotFoundException;
import com.example.contactsApp.domain.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface PhoneNumberRepository  extends JpaRepository<PhoneNumber, Long> {
    default PhoneNumber getPhoneById(String id) {
        return findPhoneNumberByPhoneNumber(id).orElseThrow(() -> new PhoneDoesNotExistException(String.format("phone with %s was not found.",id)));
    }

    default PhoneNumber getPhoneNumberByPhoneNumber(String phoneNumber) {
        return findPhoneNumberByPhoneNumber(phoneNumber).orElseThrow(() -> new PhoneNotFoundException(String.format("phone with %s was not found.",phoneNumber)));
    }

    @Transactional
    void deletePhoneNumberByPhoneNumber(String phoneNumber);

    @Query("SELECT u FROM PhoneNumber u WHERE u.phoneNumber = ?1 ")
    Optional<PhoneNumber> findPhoneNumberByPhoneNumber(String phoneNumber);
}
