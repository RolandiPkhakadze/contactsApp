package com.example.contactsApp.service;

import com.example.contactsApp.entity.PhoneNumber;

public interface PhoneNumberService {
    void addUserPhone(PhoneNumber phone, Long userId);

    void addContactPhone(PhoneNumber phone);

    void deletePhone(Long phoneId);
}
