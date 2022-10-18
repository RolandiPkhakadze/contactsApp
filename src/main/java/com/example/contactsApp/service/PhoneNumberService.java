package com.example.contactsApp.service;

import com.example.contactsApp.entity.Contact;
import com.example.contactsApp.entity.PhoneNumber;
import com.example.contactsApp.entity.User;

import java.util.List;

public interface PhoneNumberService {
    List<PhoneNumber> GetPhoneNumbers();
    List<PhoneNumber> getAllPhoneNumbersByUser(User user);
    void addUserPhone(PhoneNumber phone, Long userId);

    void addContactPhone(PhoneNumber phone, Long contactId);
}
