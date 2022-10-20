package com.example.contactsApp.service.providerServices;


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
    public NumberProvider addProvider(NumberProvider provider) {
        return numberProviderRepository.save(provider);
    }



    @Override
    public void deleteProvider(Long providerId) {
        numberProviderRepository.deleteById(providerId);
    }

    @Override
    public NumberProvider addProviderPartially(NumberProvider provider, Long id) {
        numberProviderRepository.getNumberProviderById(id);
        provider.setId(id);
        return provider;
    }

    @Override
    public NumberProvider updateProvider(NumberProvider provider, Long id) {
        NumberProvider providerForSave = numberProviderRepository.getNumberProviderById(id);
        String name = provider.getName();
        providerForSave.setName(name !=null? name : providerForSave.getName());
        Boolean isGeorgian = provider.getIsGeorgian();
        providerForSave.setIsGeorgian(isGeorgian !=null? isGeorgian : providerForSave.getIsGeorgian());
        Integer tariffForGeo = provider.getTariffForGeo();
        providerForSave.setTariffForGeo(tariffForGeo !=null? tariffForGeo : providerForSave.getTariffForGeo());
        Integer tariffForNonGeo = provider.getTariffForNonGeo();
        providerForSave.setTariffForNonGeo(tariffForNonGeo !=null? tariffForNonGeo : providerForSave.getTariffForNonGeo());
        Integer tariffForSame = provider.getTariffForSame();
        providerForSave.setTariffForSame(tariffForSame !=null? tariffForSame : providerForSave.getTariffForSame());

        return null;
    }
}
