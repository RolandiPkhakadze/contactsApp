package com.example.contactsApp.service.Impl;

import com.example.contactsApp.entity.*;
import org.hibernate.annotations.Source;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import java.time.LocalDateTime;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public class CustomMapperImpl {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "isFavorite", target = "isFavorite")
    static public Contact contactNullExclude(Contact contactFromBase, Contact contactFromApi) {
        return contactFromBase;
    }
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "username",  target = "username")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "email", target = "email")
    public static User userNullExclude(User userFromBase, @MappingTarget User userFromApi) {
        return userFromBase;
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    static public PhoneNumber phoneNullExclude(PhoneNumber phoneFromBase, PhoneNumber phoneFromApi) {
        String phoneNumber = phoneFromApi.getPhoneNumber();
        phoneFromBase.setPhoneNumber(phoneNumber !=null? phoneNumber : phoneFromBase.getPhoneNumber());
        Long balance = phoneFromApi.getBalance();
        phoneFromBase.setBalance(balance !=null? balance : phoneFromBase.getBalance());

        return phoneFromBase;
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    static public NumberProvider providerNullExclude(NumberProvider providerFromBase, NumberProvider providerFromApi) {
        String name = providerFromApi.getName();
        providerFromBase.setName(name !=null? name : providerFromBase.getName());
        Boolean isGeorgian = providerFromApi.getIsGeorgian();
        providerFromBase.setIsGeorgian(isGeorgian !=null? isGeorgian : providerFromBase.getIsGeorgian());
        Integer tariffForGeo = providerFromApi.getTariffForGeo();
        providerFromBase.setTariffForGeo(tariffForGeo !=null? tariffForGeo : providerFromBase.getTariffForGeo());
        Integer tariffForNonGeo = providerFromApi.getTariffForNonGeo();
        providerFromBase.setTariffForNonGeo(tariffForNonGeo !=null? tariffForNonGeo : providerFromBase.getTariffForNonGeo());
        Integer tariffForSame = providerFromApi.getTariffForSame();
        providerFromBase.setTariffForSame(tariffForSame !=null? tariffForSame : providerFromBase.getTariffForSame());

        return providerFromBase;
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    static public History historyNullExclude(History historyFromBase, History historyFromApi) {

        LocalDateTime startDate = historyFromApi.getStartTime();
        historyFromBase.setStartTime(startDate !=null? startDate :historyFromBase.getStartTime());
        LocalDateTime endDate = historyFromApi.getEndTime();
        historyFromBase.setEndTime(endDate !=null? endDate :historyFromBase.getEndTime());

        return historyFromBase;
    }
}
