package com.example.contactsApp.service.Impl;


import com.example.contactsApp.entity.NumberProvider;
import com.example.contactsApp.repository.NumberProviderRepository;
import com.example.contactsApp.service.Intf.NumberProviderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class NumberProviderServiceImpl implements NumberProviderService {
    private NumberProviderRepository numberProviderRepository;

    @Transactional
    @Override
    public NumberProvider addProvider(NumberProvider provider) {
        return numberProviderRepository.save(provider);
    }



    @Transactional
    @Override
    public void deleteProvider(Long providerId) {
        numberProviderRepository.deleteById(providerId);
    }

    @Transactional
    @Override
    public NumberProvider addProviderPartially(NumberProvider provider, Long id) {
        numberProviderRepository.getNumberProviderById(id);
        provider.setId(id);
        return provider;
    }

    @Transactional
    @Override
    public NumberProvider updateProvider(NumberProvider provider, Long id) {
        NumberProvider providerForSave = numberProviderRepository.getNumberProviderById(id);

        return numberProviderRepository.save(CustomMapperImpl.providerNullExclude(providerForSave,provider));
    }
}
