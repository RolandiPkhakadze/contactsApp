package com.example.contactsApp.repository;

import com.example.contactsApp.entity.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneNumberRepository  extends JpaRepository<PhoneNumber, Long> {
}
