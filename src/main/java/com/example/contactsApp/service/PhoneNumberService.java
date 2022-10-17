package com.example.contactsApp.service;

import com.example.contactsApp.entity.History;
import com.example.contactsApp.entity.PhoneNumber;
import com.example.contactsApp.repository.PhoneNumberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneNumberService {

    private PhoneNumberRepository phoneNumberRepository;

    public PhoneNumberService(PhoneNumberRepository phoneNumberRepository) {
        this.phoneNumberRepository = phoneNumberRepository;
    }

    public List<PhoneNumber> GetPhoneNumbers(){
        return  phoneNumberRepository.findAll();
    }
}
