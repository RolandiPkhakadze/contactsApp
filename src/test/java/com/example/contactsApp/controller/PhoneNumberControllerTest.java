package com.example.contactsApp.controller;


import com.example.contactsApp.dto.PhoneDto;
import com.example.contactsApp.domain.User;
import com.example.contactsApp.repository.PhoneNumberRepository;
import com.example.contactsApp.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class PhoneNumberControllerTest {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PhoneNumberController phoneNumberController;
    @Autowired
    private final PhoneNumberRepository phoneNumberRepository;

    private static final String NAME = "testname";
    private static final String EMAIL = "test@mail.com";
    private static final String PASSWORD = "TestPassword12";

    private static final String PHONE_NUMBER = "598975276";

    private static final Long BALANCE = 3L;

    @Autowired
    public PhoneNumberControllerTest(UserRepository userRepository, PhoneNumberController phoneNumberController, PhoneNumberRepository phoneNumberRepository) {
        this.userRepository = userRepository;
        this.phoneNumberController = phoneNumberController;
        this.phoneNumberRepository = phoneNumberRepository;
    }

    private User registerUser() {
        var testUser = new User();
        testUser.setEmail(EMAIL);
        testUser.setUsername(NAME);
        testUser.setPassword(PASSWORD);

        return userRepository.save(testUser);
    }

    private PhoneDto initPhoneNumber() {
        return  PhoneDto.builder()
                .phoneNumber(PHONE_NUMBER)
                .balance(BALANCE)
                .build();
    }

    private PhoneDto addPhone(User user) {
        var phone =  initPhoneNumber();
        return phoneNumberController.addUserPhone(user.getId(), phone);
    }

    @Test
    void addUserPhoneTest() {
        var user = registerUser();
        var phone = addPhone(user);

        Assertions.assertEquals(phone.getPhoneNumber(), PHONE_NUMBER);
        Assertions.assertEquals(phone.getBalance(), BALANCE);

        phoneNumberRepository.deletePhoneNumberByPhoneNumber(phone.getPhoneNumber());
        userRepository.deleteById(user.getId());

    }

    @Test
    void addContactPhoneTest() {
        var phone = initPhoneNumber();
        var contactPhone = phoneNumberController.addContactPhone(phone);

        Assertions.assertEquals(phone.getPhoneNumber(), contactPhone.getPhoneNumber());
        Assertions.assertEquals(phone.getBalance(), contactPhone.getBalance());

        phoneNumberRepository.deletePhoneNumberByPhoneNumber(phone.getPhoneNumber());

    }

    @Test
    void updatePhoneTest() {
        var user = registerUser();
        var phone = addPhone(user);
        phone.setBalance(BALANCE + BALANCE);

        var updated = phoneNumberController.updatePhone(phone);

        Assertions.assertEquals(phone.getPhoneNumber(), updated.getPhoneNumber());
        Assertions.assertEquals(phone.getBalance(), updated.getBalance());

        phoneNumberRepository.deletePhoneNumberByPhoneNumber(phone.getPhoneNumber());
        userRepository.deleteById(user.getId());
    }

    @Test
    void updatePhonePartiallyTest() {
        var user = registerUser();
        var phone = addPhone(user);
        phone.setBalance(BALANCE + BALANCE);

        var updated = phoneNumberController.updatePhonePartially(phone);

        Assertions.assertEquals(phone.getPhoneNumber(), updated.getPhoneNumber());
        Assertions.assertEquals(phone.getBalance(), updated.getBalance());

        phoneNumberRepository.deletePhoneNumberByPhoneNumber(phone.getPhoneNumber());
        userRepository.deleteById(user.getId());
    }

    @Test
    void deletePhoneTest() {
        var user = registerUser();
        var phone = addPhone(user);

        Assertions.assertEquals("provider deleted", phoneNumberController.deletePhone(phone.getPhoneNumber()));

        //Assertions.assertThrows(PhoneDoesNotExistException.class, () -> phoneNumberController.deletePhone(phone.getPhoneNumber()));

        phoneNumberRepository.deletePhoneNumberByPhoneNumber(phone.getPhoneNumber());
        userRepository.deleteById(user.getId());

    }

}
