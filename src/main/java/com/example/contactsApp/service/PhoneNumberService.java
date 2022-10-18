package com.example.contactsApp.service;

import com.example.contactsApp.entity.Contact;
import com.example.contactsApp.entity.PhoneNumber;

import java.util.List;

public interface PhoneNumberService {
    List<PhoneNumber> GetPhoneNumbers();

    void addUserPhone(PhoneNumber phone, Long userId);

    void addContactPhone(PhoneNumber phone, Long contactId);
}
