package com.example.contactsApp.repository;

import com.example.contactsApp.entity.PhoneNumber;
import com.example.contactsApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneNumberRepository  extends JpaRepository<PhoneNumber, Long> {
    List<PhoneNumber> getPhoneNumbersByUser(User user);
}
