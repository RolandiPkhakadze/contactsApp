package com.example.contactsApp.service.Impl;

import com.example.contactsApp.domain.PhoneNumber;
import com.example.contactsApp.domain.User;
import com.example.contactsApp.repository.PhoneNumberRepository;
import com.example.contactsApp.repository.UserRepository;
import com.example.contactsApp.service.PhoneNumberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class PhoneNumberServiceImpl implements PhoneNumberService {
    private final UserRepository userRepository;
    private final PhoneNumberRepository phoneNumberRepository;
    private final CustomMapper mapper;

    @Transactional
    @Override
    public PhoneNumber addUserPhone(PhoneNumber phone, Long userId) {
        User userOptional = userRepository.getUserById(userId);

        phone.setUser(userOptional);
        phone = phoneNumberRepository.save(phone);

        log.debug(String.format("user with id: %d added phone with phone number: %s",userId,phone.getPhoneNumber()));
        return phone;
    }

    @Transactional
    @Override
    public PhoneNumber addContactPhone(PhoneNumber phone) {
        phone = phoneNumberRepository.save(phone);

        log.debug(String.format("contact's phone number %s was added",phone.getPhoneNumber()));
        return phone;
    }

    @Transactional
    @Override
    public void deletePhone(String phoneNumber) {
        phoneNumberRepository.deletePhoneNumberByPhoneNumber(phoneNumber);
        log.debug(String.format("Phone number %s was deleted",phoneNumber));
    }

    @Transactional
    @Override
    public PhoneNumber updatePhone(PhoneNumber phone) {
        phoneNumberRepository.getPhoneById(phone.getPhoneNumber());
        phone = phoneNumberRepository.save(phone);
        log.debug(String.format("phone: %s was updated",phone.getPhoneNumber()));
        return phone;
    }

    @Transactional
    @Override
    public PhoneNumber updatePhonePartially(PhoneNumber phone) {
        PhoneNumber phoneForSave = phoneNumberRepository.getPhoneById(phone.getPhoneNumber());
        phone = phoneNumberRepository.save(mapper.phoneNullExclude(phoneForSave,phone));
        log.debug(String.format("phone: %s was updated",phone.getPhoneNumber()));
        return phone;
    }

    @Override
    public PhoneNumber getPhoneNumberByPhoneNumber(String phoneNumber) {
        PhoneNumber phone = phoneNumberRepository.getPhoneNumberByPhoneNumber(phoneNumber);
        log.debug(String.format("phone: %s was requested and was returned successfully",phoneNumber));
        return phone;
    }

}
