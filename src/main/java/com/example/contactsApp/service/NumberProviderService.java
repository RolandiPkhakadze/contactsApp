package com.example.contactsApp.service;

import com.example.contactsApp.entity.NumberProvider;

public interface NumberProviderService {
    void addProvider(NumberProvider provider);

    void deleteProvider(Long providerId);
}
