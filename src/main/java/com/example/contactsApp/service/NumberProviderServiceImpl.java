package com.example.contactsApp.service;


import com.example.contactsApp.entity.NumberProvider;
import com.example.contactsApp.repository.NumberProviderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class NumberProviderServiceImpl implements NumberProviderService{
    private NumberProviderRepository numberProviderRepository;

    @Override
    public List<NumberProvider> GetNumberProviders() {
        return numberProviderRepository.findAll();
    }
}
