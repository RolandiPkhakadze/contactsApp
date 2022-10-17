package com.example.contactsApp.Service;


import com.example.contactsApp.repository.NumberProviderRepository;
import org.springframework.stereotype.Service;

@Service
public class NumberProviderService {
    private NumberProviderRepository numberProviderRepository;

    public NumberProviderService(NumberProviderRepository numberProviderRepository) {
        this.numberProviderRepository = numberProviderRepository;
    }
}
