package com.example.contactsApp.service.phoneServices;

import com.example.contactsApp.entity.PhoneNumber;

public interface PhoneNumberService {
    void addUserPhone(PhoneNumber phone, Long userId);

    PhoneNumber addContactPhone(PhoneNumber phone);

    void deletePhone(Long phoneId);
}
