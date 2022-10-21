package com.example.contactsApp.service.Impl;

import com.example.contactsApp.Exception.PhoneDoesNotExistException;
import com.example.contactsApp.Exception.ProviderDoesNotExistException;
import com.example.contactsApp.entity.PhoneNumber;
import com.example.contactsApp.entity.User;
import com.example.contactsApp.repository.PhoneNumberRepository;
import com.example.contactsApp.repository.UserRepository;
import com.example.contactsApp.service.Intf.PhoneNumberService;
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

    @Transactional(rollbackFor = PhoneDoesNotExistException.class)
    @Override
    public PhoneNumber addUserPhone(PhoneNumber phone, Long userId) {
        User userOptional = userRepository.getUserById(userId);
        log.debug("dsfdsfsd");

        phone.setUser(userOptional);
        return phoneNumberRepository.save(phone);
    }

    @Transactional(rollbackFor = PhoneDoesNotExistException.class)
    @Override
    public PhoneNumber addContactPhone(PhoneNumber phone) {
        return phoneNumberRepository.save(phone);
    }

    @Transactional(rollbackFor = PhoneDoesNotExistException.class)
    @Override
    public void deletePhone(Long phoneId) {
        phoneNumberRepository.deleteById(phoneId);
    }

    @Transactional(rollbackFor = PhoneDoesNotExistException.class)
    @Override
    public PhoneNumber updatePhone(PhoneNumber phone, Long id) {
        phoneNumberRepository.getPhoneById(id);
        phone.setId(id);
        return phoneNumberRepository.save(phone);
    }

    @Transactional(rollbackFor = PhoneDoesNotExistException.class)
    @Override
    public PhoneNumber updatePhonePartially(PhoneNumber phone, Long id) {
        PhoneNumber phoneForSave = phoneNumberRepository.getPhoneById(id);
        return phoneNumberRepository.save(CustomMapperImpl.phoneNullExclude(phoneForSave,phone));
    }

    @Override
    public PhoneNumber getPhoneNumberByPhoneNumber(String phoneNumber) {
        return phoneNumberRepository.getPhoneNumberByPhoneNumber(phoneNumber);
    }

}