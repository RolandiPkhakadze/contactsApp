package com.example.contactsApp.dtoConverter.converter;

import com.example.contactsApp.dtoConverter.dtoModel.ProviderDto;
import com.example.contactsApp.entity.NumberProvider;
import org.springframework.stereotype.Component;

@Component
public class ProviderConverter {
    public ProviderDto entityToDto(NumberProvider provider){
        ProviderDto dto = new ProviderDto();

        dto.setId(provider.getId());
        dto.setName(provider.getName());
        dto.setIsGeorgian(provider.getIsGeorgian());
        dto.setTariffForGeo(provider.getTariffForGeo());
        dto.setTariffForSame(provider.getTariffForSame());
        dto.setTariffForNonGeo(provider.getTariffForNonGeo());
        return dto;
    }


    public NumberProvider dtoToEntity(ProviderDto dto){
        NumberProvider provider = new NumberProvider();

        provider.setName(dto.getName());
        provider.setIsGeorgian(dto.getIsGeorgian());
        provider.setTariffForGeo(dto.getTariffForGeo());
        provider.setTariffForSame(dto.getTariffForSame());
        provider.setTariffForNonGeo(dto.getTariffForNonGeo());
        return provider;
    }
}
