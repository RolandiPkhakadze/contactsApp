package com.example.contactsApp.service;

import com.example.contactsApp.dto.ProviderDto;

public interface NumberProviderService {
    ProviderDto addProvider(ProviderDto providerDto);

    void deleteProvider(Long providerId);

    ProviderDto addProviderPartially(ProviderDto provider, Long id);

    ProviderDto updateProvider(ProviderDto providerDto, Long id);
}
