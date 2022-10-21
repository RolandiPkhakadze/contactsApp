package com.example.contactsApp.service;

import com.example.contactsApp.entity.PhoneNumber;

public interface PhoneNumberService {
    PhoneNumber addUserPhone(PhoneNumber phone, Long userId);

    PhoneNumber addContactPhone(PhoneNumber phone);

    void deletePhone(Long phoneId);

    PhoneNumber updatePhone(PhoneNumber phone);

    PhoneNumber updatePhonePartially(PhoneNumber phone);

    PhoneNumber getPhoneNumberByPhoneNumber(String phoneNumber);
}
