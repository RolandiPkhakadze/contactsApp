package com.example.contactsApp.service;

import com.example.contactsApp.entity.PhoneNumber;

public interface PhoneNumberService {
    PhoneNumber addUserPhone(PhoneNumber phone, Long userId);

    PhoneNumber addContactPhone(PhoneNumber phone);

    PhoneNumber updatePhone(PhoneNumber phone);

    PhoneNumber updatePhonePartially(PhoneNumber phone);

    void deletePhone(String phoneNumber);

    PhoneNumber getPhoneNumberByPhoneNumber(String phoneNumber);
}
