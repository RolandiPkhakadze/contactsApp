package com.example.contactsApp.service.phoneServices;

import com.example.contactsApp.entity.PhoneNumber;

public interface PhoneNumberService {
    PhoneNumber addUserPhone(PhoneNumber phone, Long userId);

    PhoneNumber addContactPhone(PhoneNumber phone);

    void deletePhone(Long phoneId);

    PhoneNumber updatePhone(PhoneNumber phone, Long id);

    PhoneNumber updatePhonePartially(PhoneNumber phone, Long id);

    PhoneNumber getPhoneNumberByPhoneNumber(String phoneNumber);
}
