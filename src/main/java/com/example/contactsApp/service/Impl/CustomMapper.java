package com.example.contactsApp.service.Impl;

import com.example.contactsApp.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

import java.lang.annotation.Target;
import java.time.LocalDateTime;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.ReportingPolicy.IGNORE;

@Component
@Mapper(componentModel = "spring", nullValueCheckStrategy = ALWAYS, unmappedTargetPolicy = IGNORE)
public abstract class CustomMapper {

    @Mapping(source = "firstName", target = "firstName",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "lastName", target = "lastName",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "isFavorite", target = "isFavorite",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Contact contactNullExclude(@MappingTarget Contact contactFromBase,  Contact contactFromApi);


    @Mapping(source = "email", target = "email",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "password", target = "password",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "username", target = "username",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract User userNullExclude(@MappingTarget User userFromBase, User userFromApi);

    @Mapping(source = "balance", target = "balance",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract PhoneNumber phoneNullExclude(@MappingTarget PhoneNumber phoneFromBase, PhoneNumber phoneFromApi);

    @Mapping(source = "name", target = "name",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "isGeorgian", target = "isGeorgian",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "tariffForGeo", target = "tariffForGeo",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "tariffForNonGeo", target = "tariffForNonGeo",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "tariffForSame", target = "tariffForSame",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract NumberProvider providerNullExclude(@MappingTarget NumberProvider providerFromBase, NumberProvider providerFromApi);

    @Mapping(source = "startTime", target = "startTime",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "endTime", target = "endTime",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract History historyNullExclude(@MappingTarget History historyFromBase, History historyFromApi);
}
