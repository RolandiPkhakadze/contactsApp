package com.example.contactsApp.service.Impl;


import com.example.contactsApp.converter.ProviderConverter;
import com.example.contactsApp.dto.ProviderDto;
import com.example.contactsApp.domain.NumberProvider;
import com.example.contactsApp.repository.NumberProviderRepository;
import com.example.contactsApp.service.NumberProviderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@AllArgsConstructor
@Slf4j
@Service
public class NumberProviderServiceImpl implements NumberProviderService {
    private final CustomMapper mapper; // contains method which are related to ProviderConverter, need to think about
    // 1 mapper
    // or refactor somehow because here you're violating the rule responsibility principle
    private final ProviderConverter converter;
    private final NumberProviderRepository numberProviderRepository;

    // rollbackFor and rollbackForClassName – define one or more Throwable classes
    // for which the current transaction will be rolled back. By default,
    // a transaction is rolled back if a RuntimException or an Error is thrown,
    // but not if it throws a checked Exception.
    // therefore you can remove your rollbackFor in my opinion
    @Transactional
    @Override
    public ProviderDto addProvider(ProviderDto providerDto) {
        NumberProvider provider = converter.toEntity(providerDto);
        NumberProvider savedProvider = numberProviderRepository.save(provider);
        log.debug("provider added with id " + savedProvider.getId());

        return converter.toDto(savedProvider);
    }


    @Transactional
    @Override
    public void deleteProvider(Long providerId) {
        numberProviderRepository.deleteById(providerId);
        log.debug(String.format("provider with id: %d was deleted", providerId));
    }

    @Transactional
    @Override
    public ProviderDto updateProvider(ProviderDto providerDto, Long id) {
        NumberProvider provider = numberProviderRepository.getNumberProviderById(id);
        // I don't know why you update here id, because this is duty of sequence generator/db or etc.
        // if you update PK for entity it is really strange O_o
        // a lot of updated values can come into dto, so you need to use a mapper here
        // here a simple case but in complex logic dto may have difference structure and etc.
        converter.update(provider, converter.toEntity(providerDto));
        numberProviderRepository.save(provider);
        log.debug(String.format("Provider with id: %d was updated", id));

        return converter.toDto(provider);
    }

    @Transactional
    @Override
    public ProviderDto addProviderPartially(ProviderDto providerDto, Long id) {
        NumberProvider providerForSave = numberProviderRepository.getNumberProviderById(id);
        NumberProvider updatedProvider = numberProviderRepository.save(
                mapper.providerNullExclude(providerForSave, converter.toEntity(providerDto)));
        log.debug(String.format("Provider with id: %d was updated", id));
        return converter.toDto(updatedProvider);
    }
}
