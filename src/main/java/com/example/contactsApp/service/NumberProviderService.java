package com.example.contactsApp.service;


import com.example.contactsApp.entity.History;
import com.example.contactsApp.entity.NumberProvider;
import com.example.contactsApp.repository.NumberProviderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NumberProviderService {
    private NumberProviderRepository numberProviderRepository;

    public NumberProviderService(NumberProviderRepository numberProviderRepository) {
        this.numberProviderRepository = numberProviderRepository;
    }

    public List<NumberProvider> GetNumberProviders(){
        return  numberProviderRepository.findAll();
    }
}
