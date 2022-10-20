package com.example.contactsApp.dtoConverter.converter;

import com.example.contactsApp.dtoConverter.dtoModel.ProviderDto;
import com.example.contactsApp.entity.NumberProvider;
import org.springframework.stereotype.Component;

@Component
public class ProviderConverter {
    public ProviderDto entityToDto(NumberProvider provider){

        return ProviderDto.builder().
                id(provider.getId()).
                isGeorgian(provider.getIsGeorgian()).
                name(provider.getName()).
                tariffForGeo(provider.getTariffForGeo()).
                tariffForNonGeo(provider.getTariffForSame()).
                tariffForSame(provider.getTariffForNonGeo()).
                build();
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
