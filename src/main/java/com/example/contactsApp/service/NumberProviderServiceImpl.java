package com.example.contactsApp.service;


import com.example.contactsApp.entity.NumberProvider;
import com.example.contactsApp.repository.NumberProviderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class NumberProviderServiceImpl implements NumberProviderService{
    private NumberProviderRepository numberProviderRepository;

    @Override
    public void addProvider(NumberProvider provider) {
        numberProviderRepository.save(provider);
    }

    @Override
    public void deleteProvider(Long providerId) {
        numberProviderRepository.deleteById(providerId);
    }
}
