package com.example.contactsApp.service.Impl;


import com.example.contactsApp.Exception.ProviderDoesNotExistException;
import com.example.contactsApp.entity.NumberProvider;
import com.example.contactsApp.repository.NumberProviderRepository;
import com.example.contactsApp.service.NumberProviderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class NumberProviderServiceImpl implements NumberProviderService {
    private NumberProviderRepository numberProviderRepository;
    private final CustomMapper mapper;

    @Transactional(rollbackFor = ProviderDoesNotExistException.class)
    @Override
    public NumberProvider addProvider(NumberProvider provider) {
        provider = numberProviderRepository.save(provider);
        log.debug("provider added with id "+provider.getId());
        return provider;
    }



    @Transactional(rollbackFor = ProviderDoesNotExistException.class)
    @Override
    public void deleteProvider(Long providerId) {

        numberProviderRepository.deleteById(providerId);
        log.debug(String.format("provider with id: %d was deleted",providerId));
    }

    @Transactional(rollbackFor = ProviderDoesNotExistException.class)
    @Override
    public NumberProvider updateProvider (NumberProvider provider, Long id) {
        numberProviderRepository.getNumberProviderById(id);
        provider.setId(id);
        log.debug(String.format("Provider with id: %d was updated",id));
        return provider;
    }

    @Transactional(rollbackFor = ProviderDoesNotExistException.class)
    @Override
    public NumberProvider addProviderPartially(NumberProvider provider, Long id) {
        NumberProvider providerForSave = numberProviderRepository.getNumberProviderById(id);
        provider = numberProviderRepository.save(mapper.providerNullExclude(providerForSave,provider));
        log.debug(String.format("Provider with id: %d was updated",id));
        return provider;
    }
}
