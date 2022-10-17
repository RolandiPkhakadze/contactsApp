package com.example.contactsApp.Service;

import com.example.contactsApp.repository.PhoneNumberRepository;
import org.springframework.stereotype.Service;

@Service
public class PhoneNumberService {

    private PhoneNumberRepository phoneNumberRepository;

    public PhoneNumberService(PhoneNumberRepository phoneNumberRepository) {
        this.phoneNumberRepository = phoneNumberRepository;
    }
}
