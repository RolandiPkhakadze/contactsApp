package com.example.contactsApp.service.Impl;

import com.example.contactsApp.Exception.PhoneNotFoundException;
import com.example.contactsApp.entity.PhoneNumber;
import com.example.contactsApp.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
public class PhoneNumberServiceTest {
    @Container
    private static final PostgreSQLContainer container = (PostgreSQLContainer) new PostgreSQLContainer("postgres:latest").withReuse(true);

    @DynamicPropertySource
    public static void overrideProps(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url",container::getJdbcUrl);
        registry.add("spring.datasource.username",container::getUsername);
        registry.add("spring.datasource.password",container::getPassword);

    }
    private final UserServiceImpl userService;
    private final PhoneNumberServiceImpl phoneNumberService;
    private static final String NAME = "testname";
    private static final String EMAIL = "test@mail.com";
    private static final String PASSWORD = "TestPassword12";

    private static final String PHONE_NUMBER = "598975276";

    private static final Long BALANCE = 3L;


    @Autowired
    public PhoneNumberServiceTest(UserServiceImpl userService, PhoneNumberServiceImpl phoneNumberService) {
        this.userService = userService;
        this.phoneNumberService = phoneNumberService;
    }

    private User registerUser() {
        var testUser = new User();
        testUser.setEmail(EMAIL);
        testUser.setUsername(NAME);
        testUser.setPassword(PASSWORD);

        return userService.registerUser(testUser);
    }

    private PhoneNumber initPhoneNumber() {
        return  PhoneNumber.builder()
                .phoneNumber(PHONE_NUMBER)
                .balance(BALANCE)
                .build();
    }

    private PhoneNumber addPhone(User user) {
        var phone =  initPhoneNumber();
        return phoneNumberService.addUserPhone(phone, user.getId());
    }

    @Test
    void addUserPhoneTest() {
        var user = registerUser();
//        var expected = initPhoneNumber();
//        expected.setUser(userService.loginUser(user.getEmail(), user.getPassword()));
        var phone = addPhone(user);



        Assertions.assertEquals(phone.getPhoneNumber(),PHONE_NUMBER);
        Assertions.assertEquals(phone.getBalance(), BALANCE);
        Assertions.assertEquals(phone.getUser().getId(), user.getId());

//        org.assertj.core.api.Assertions.assertThat(expected).usingRecursiveComparison().ignoringFields("id", "historyList").isEqualTo(phone);

        phoneNumberService.deletePhone(phone.getPhoneNumber());
        userService.deleteUser(user.getId());

    }

    @Test
    void deletePhoneTest() {
        var user = registerUser();
        var phone = addPhone(user);

        phoneNumberService.deletePhone(phone.getPhoneNumber());

        Assertions.assertThrows(PhoneNotFoundException.class, () -> phoneNumberService.getPhoneNumberByPhoneNumber(phone.getPhoneNumber()));

        userService.deleteUser(user.getId());

    }

    @Test
    void updatePhoneTest() {
        var user = registerUser();
        var phone = addPhone(user);

        phone.setBalance(BALANCE + 4L);

        var updated = phoneNumberService.updatePhone(phone);

        Assertions.assertEquals(phone.getBalance(), updated.getBalance());

        phoneNumberService.deletePhone(phone.getPhoneNumber());
        userService.deleteUser(user.getId());

    }

    @Test
    void updateUserPhonePartiallyTest() {
        var user = registerUser();
        var phone = addPhone(user);

        phone.setBalance(BALANCE+4L);
        PhoneNumber phoneToSend = PhoneNumber.builder().phoneNumber(phone.getPhoneNumber()).balance(BALANCE+4L).build();

        var updated = phoneNumberService.updatePhonePartially(phoneToSend);

        Assertions.assertEquals(phone.getBalance(), updated.getBalance());

        phoneNumberService.deletePhone(phone.getPhoneNumber());
        userService.deleteUser(user.getId());

    }

    @Test
    void addContactPhoneTest() {
        var phone = initPhoneNumber();

        var added = phoneNumberService.addContactPhone(phone);

        Assertions.assertEquals(phone.getBalance(), added.getBalance());
        Assertions.assertEquals(phone.getPhoneNumber(), added.getPhoneNumber());
        Assertions.assertNull(added.getUser());


//        org.assertj.core.api.Assertions.assertThat(phone).usingRecursiveComparison().ignoringFields("id", "historyList").isEqualTo(added);

        phoneNumberService.deletePhone(phone.getPhoneNumber());

    }



}
