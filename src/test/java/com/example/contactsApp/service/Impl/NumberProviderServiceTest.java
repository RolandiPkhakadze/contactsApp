package com.example.contactsApp.service.Impl;

import com.example.contactsApp.entity.History;
import com.example.contactsApp.entity.NumberProvider;
import com.example.contactsApp.repository.NumberProviderRepository;
import com.example.contactsApp.service.NumberProviderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.Duration;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
public class NumberProviderServiceTest {

    @Autowired
    private NumberProviderRepository numberProviderRepository;

    @Autowired
    private NumberProviderService numberProviderService;

    public static NumberProvider getProvider(){

        return NumberProvider.builder()
                .name("BEELINE")
                .isGeorgian(false)
                .tariffForSame(3)
                .tariffForGeo(5)
                .tariffForNonGeo(10)
                .build();
    }

    @Test
    public void addProviderTest(){
        NumberProvider provider = numberProviderService.addProvider(getProvider());
        NumberProvider providerFromBase = numberProviderRepository.getNumberProviderById(provider.getId());

        Assertions.assertEquals(provider.toString(),providerFromBase.toString());

        numberProviderRepository.deleteById(provider.getId());
    }

    @Test
    public void deleteProviderTest(){
        NumberProvider provider = numberProviderRepository.save(getProvider());

        numberProviderService.deleteProvider(provider.getId());

        Assertions.assertEquals(Optional.empty(), numberProviderRepository.findNumberProviderById(provider.getId()));
    }

    @Test
    public void addProviderPartiallyTest(){
        NumberProvider provider = numberProviderRepository.save(getProvider());

        NumberProvider providerToUpdate = NumberProvider.builder().tariffForNonGeo(1).build();
        provider.setTariffForNonGeo(1);

        providerToUpdate = numberProviderService.addProviderPartially(providerToUpdate, provider.getId());
        Assertions.assertEquals(provider.toString(),providerToUpdate.toString());

        numberProviderService.deleteProvider(provider.getId());
    }

    @Test
    public void updateProviderTest(){
        NumberProvider provider = numberProviderRepository.save(getProvider());

        NumberProvider providerToUpdate = NumberProvider.builder().
                tariffForNonGeo(1)
                .tariffForGeo(4)
                .tariffForSame(6)
                .isGeorgian(true)
                .name("MAGTI")
                .build();

        provider = numberProviderService.updateProvider(providerToUpdate, provider.getId());
        Assertions.assertEquals(provider.toString(),providerToUpdate.toString());

        numberProviderService.deleteProvider(provider.getId());
    }
}
