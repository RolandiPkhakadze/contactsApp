package com.example.contactsApp.service;

import com.example.contactsApp.entity.NumberProvider;

public interface NumberProviderService {
    NumberProvider addProvider(NumberProvider provider);

    void deleteProvider(Long providerId);

    NumberProvider addProviderPartially(NumberProvider provider, Long id);

    NumberProvider updateProvider(NumberProvider provider, Long id);
}
