package com.example.contactsApp.service.Impl;

import com.example.contactsApp.entity.NumberProvider;
import com.example.contactsApp.entity.PhoneNumber;
import com.example.contactsApp.entity.User;
import com.example.contactsApp.repository.NumberProviderRepository;
import com.example.contactsApp.repository.PhoneNumberRepository;
import com.example.contactsApp.repository.UserRepository;
import com.example.contactsApp.service.PhoneNumberService;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class PhoneNumberServiceImplTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    @Autowired
    private NumberProviderRepository numberProviderRepository;

    @Autowired
    private PhoneNumberService phoneNumberService;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        phoneNumberRepository.deleteAll();
        numberProviderRepository.deleteAll();
    }

    @Test
    void addUserPhoneTest() {
        // given
        User user = new User("gigi.tsitr@gmail.com", "testli", "GiGi-022###@asdasd");
        User userEntity = userRepository.save(user);
        NumberProvider provider = new NumberProvider();
        NumberProvider providerEntity = numberProviderRepository.save(provider);
        PhoneNumber phoneNumber = new PhoneNumber("557643025", 15L, providerEntity);
        // when
        phoneNumberService.addUserPhone(phoneNumber, userEntity.getId());
        // then
        PhoneNumber phoneNumberFromDb = phoneNumberRepository.findPhoneNumberByPhoneNumber(phoneNumber.getPhoneNumber()).get();

//        Assertions.assertThat(phoneNumber).usingRecursiveComparison().ignoringFields("id", "historyList").isEqualTo(phoneNumberFromDb);
    }

    @Test
    void addContactPhone() {
    }

    @Test
    void deletePhone() {
    }

    @Test
    void updatePhone() {
    }

    @Test
    void updatePhonePartially() {
    }

    @Test
    void getPhoneNumberByPhoneNumber() {
    }
}