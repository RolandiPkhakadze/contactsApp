package com.example.contactsApp.service.Impl;

import com.example.contactsApp.entity.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

public class CustomMapperImpl {

    static public Contact contactNullExclude(Contact contactFromBase, Contact contactFromApi) {
        var firstName = contactFromApi.getFirstName();
        contactFromBase.setFirstName(firstName != null ? firstName : contactFromBase.getFirstName());
        var lastName = contactFromApi.getLastName();
        contactFromBase.setLastName(lastName != null ? lastName : contactFromBase.getLastName());
        var isFavorite = contactFromApi.getIsFavorite();
        contactFromBase.setIsFavorite(isFavorite != null ? isFavorite : contactFromBase.getIsFavorite());

        return contactFromBase;
    }


    static public User userNullExclude(User userFromBase, User userFromApi) {
        String email = userFromApi.getEmail();
        userFromBase.setEmail(email !=null? email : userFromBase.getEmail());
        String password = userFromApi.getPassword();
        userFromBase.setPassword(password !=null? password : userFromBase.getPassword());
        String username = userFromApi.getUsername();
        userFromBase.setUsername(username !=null? username : userFromBase.getUsername());
        return userFromBase;
    }


    static public PhoneNumber phoneNullExclude(PhoneNumber phoneFromBase, PhoneNumber phoneFromApi) {
        String phoneNumber = phoneFromApi.getPhoneNumber();
        phoneFromBase.setPhoneNumber(phoneNumber !=null? phoneNumber : phoneFromBase.getPhoneNumber());
        Long balance = phoneFromApi.getBalance();
        phoneFromBase.setBalance(balance !=null? balance : phoneFromBase.getBalance());
        return phoneFromBase;
    }


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


    static public History historyNullExclude(History historyFromBase, History historyFromApi) {

        LocalDateTime startDate = historyFromApi.getStartTime();
        historyFromBase.setStartTime(startDate !=null? startDate :historyFromBase.getStartTime());
        LocalDateTime endDate = historyFromApi.getEndTime();
        historyFromBase.setEndTime(endDate !=null? endDate :historyFromBase.getEndTime());

        return historyFromBase;
    }
}
