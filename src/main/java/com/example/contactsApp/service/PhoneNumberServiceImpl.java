package com.example.contactsApp.service;

import com.example.contactsApp.entity.PhoneNumber;
import com.example.contactsApp.repository.PhoneNumberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PhoneNumberServiceImpl implements PhoneNumberService{

    private PhoneNumberRepository phoneNumberRepository;

    @Override
    public List<PhoneNumber> GetPhoneNumbers() {
        return  phoneNumberRepository.findAll();
    }
}
