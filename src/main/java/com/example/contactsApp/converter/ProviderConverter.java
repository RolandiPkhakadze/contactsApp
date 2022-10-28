package com.example.contactsApp.converter;

import com.example.contactsApp.dto.ProviderDto;
import com.example.contactsApp.domain.NumberProvider;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface ProviderConverter {
    ProviderDto toDto(NumberProvider provider);

    NumberProvider toEntity(ProviderDto dto);

    void update(@MappingTarget NumberProvider to, NumberProvider from);
}
