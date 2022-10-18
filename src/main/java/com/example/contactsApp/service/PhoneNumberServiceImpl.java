package com.example.contactsApp.service;

import com.example.contactsApp.entity.Contact;
import com.example.contactsApp.entity.PhoneNumber;
import com.example.contactsApp.entity.User;
import com.example.contactsApp.repository.PhoneNumberRepository;
import com.example.contactsApp.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PhoneNumberServiceImpl implements PhoneNumberService{
    private final UserRepository userRepository;
    private final PhoneNumberRepository phoneNumberRepository;


    @Override
    public List<PhoneNumber> GetPhoneNumbers() {
        return  phoneNumberRepository.findAll();
    }

    @Override
    public void addUserPhone(PhoneNumber phone, Long userId) {
        User userOptional = userRepository.getUserById(userId);
        log.debug("dsfdsfsd");

        phone.setUser(userOptional);
        phoneNumberRepository.save(phone);
    }

    @Override
    public void addContactPhone(PhoneNumber phone, Long contactId) {
        User userOptional = userRepository.getUserById(contactId);
        log.debug("dsfdsfsd");

        phone.setUser(userOptional);
        phoneNumberRepository.save(phone);
    }
}
