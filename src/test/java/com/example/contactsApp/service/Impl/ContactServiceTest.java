package com.example.contactsApp.service.Impl;

import com.example.contactsApp.entity.Contact;
import com.example.contactsApp.entity.PhoneNumber;
import com.example.contactsApp.entity.User;
import com.example.contactsApp.service.PhoneNumberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class ContactServiceTest {

    private final UserServiceImpl userService;
    private final ContactServiceImpl contactService;
    private final PhoneNumberService phoneNumberService;
    private static final String NAME = "testname";
    private static final String EMAIL = "test@mail.com";
    private static final String PASSWORD = "TestPassword12";

    private static final String PHONE_NUMBER = "598975276";

    private static final Long BALANCE = 3L;


    @Autowired
    public ContactServiceTest(UserServiceImpl userService, ContactServiceImpl contactService, PhoneNumberServiceImpl phoneNumberService) {
        this.userService = userService;
        this.contactService = contactService;
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
    void addContactTest() {

        var user = registerUser();
        var phone = addPhone(user);

        var contact = new Contact();

        contact.setPhoneNumber(phone);
        contact.setFirstName(NAME);
        contact.setLastName(NAME);
        contact.setIsFavorite(Boolean.TRUE);
        contact.setId(phone.getPhoneNumber());

        var contactFromDb = contactService.addContact(contact, user.getId());

        Assertions.assertEquals(contactFromDb.getUser().getId(), user.getId());
        Assertions.assertEquals(contactFromDb.getFirstName(), contact.getFirstName());
        Assertions.assertEquals(contactFromDb.getLastName(), contact.getLastName());
        Assertions.assertEquals(contactFromDb.getId(), contact.getId());
        Assertions.assertEquals(contactFromDb.getId(), phone.getPhoneNumber());
        Assertions.assertEquals(contactFromDb.getIsFavorite(), contact.getIsFavorite());


        contactService.deleteContact(contactFromDb.getId());
        phoneNumberService.deletePhone(phone.getPhoneNumber());
        userService.deleteUser(user.getId());


    }


    @Test
    void UpdateContactTest() {
        var user = registerUser();
        var phone = addPhone(user);

        var contact = Contact.builder().
                firstName(NAME).
                lastName(NAME).
                phoneNumber(phone).
                isFavorite(Boolean.TRUE).
                id(phone.getPhoneNumber()).
                build();

        contactService.addContact(contact, user.getId());

        contact = Contact.builder().
                firstName("new"+NAME).
                lastName("new"+NAME).
                phoneNumber(phone).
                isFavorite(Boolean.FALSE).
                id(phone.getPhoneNumber()).
                build();

        var contactFromDb = contactService.updateContact(contact);

        Assertions.assertEquals(contactFromDb.getUser().getId(), user.getId());
        Assertions.assertEquals(contactFromDb.getFirstName(), contact.getFirstName());
        Assertions.assertEquals(contactFromDb.getLastName(), contact.getLastName());
        Assertions.assertEquals(contactFromDb.getId(), contact.getId());
        Assertions.assertEquals(contactFromDb.getId(), phone.getPhoneNumber());
        Assertions.assertEquals(contactFromDb.getIsFavorite(), contact.getIsFavorite());


        contactService.deleteContact(contactFromDb.getId());
        phoneNumberService.deletePhone(phone.getPhoneNumber());
        userService.deleteUser(user.getId());
    }

    @Test
    void UpdateContactfirstNameTest() {
        var user = registerUser();
        var phone = addPhone(user);

        var contact = Contact.builder().
                firstName(NAME).
                lastName(NAME).
                phoneNumber(phone).
                isFavorite(Boolean.TRUE).
                id(phone.getPhoneNumber()).
                build();

        contactService.addContact(contact, user.getId());

        contact = Contact.builder().
                firstName("new"+NAME).
                lastName(NAME).
                phoneNumber(phone).
                isFavorite(Boolean.TRUE).
                id(phone.getPhoneNumber()).
                build();

        var dto = Contact.builder().id(phone.getPhoneNumber()).firstName("new"+NAME).build(); //TODO validation

        var contactFromDb = contactService.updateContactPartially(dto);

        Assertions.assertEquals(contactFromDb.getUser().getId(), user.getId());
        Assertions.assertEquals(contactFromDb.getFirstName(), contact.getFirstName());
        Assertions.assertEquals(contactFromDb.getLastName(), contact.getLastName());
        Assertions.assertEquals(contactFromDb.getId(), contact.getId());
        Assertions.assertEquals(contactFromDb.getId(), phone.getPhoneNumber());
        Assertions.assertEquals(contactFromDb.getIsFavorite(), contact.getIsFavorite());


        contactService.deleteContact(contactFromDb.getId());
        phoneNumberService.deletePhone(phone.getPhoneNumber());
        userService.deleteUser(user.getId());
    }

    @Test
    void UpdateContactIsFavoriteTest() {
        var user = registerUser();
        var phone = addPhone(user);

        var contact = Contact.builder().
                firstName(NAME).
                lastName(NAME).
                phoneNumber(phone).
                isFavorite(Boolean.TRUE).
                id(phone.getPhoneNumber()).
                build();

        contactService.addContact(contact, user.getId());

        contact = Contact.builder().
                firstName(NAME).
                lastName(NAME).
                phoneNumber(phone).
                isFavorite(Boolean.FALSE).
                id(phone.getPhoneNumber()).
                build();

        var dto = Contact.builder().isFavorite(Boolean.FALSE).id(phone.getPhoneNumber()).firstName(NAME).build(); //TODO validation

        var contactFromDb = contactService.updateContactPartially(dto);

        Assertions.assertEquals(contactFromDb.getUser().getId(), user.getId());
        Assertions.assertEquals(contactFromDb.getFirstName(), contact.getFirstName());
        Assertions.assertEquals(contactFromDb.getLastName(), contact.getLastName());
        Assertions.assertEquals(contactFromDb.getId(), contact.getId());
        Assertions.assertEquals(contactFromDb.getId(), phone.getPhoneNumber());
        Assertions.assertEquals(contactFromDb.getIsFavorite(), contact.getIsFavorite());


        contactService.deleteContact(contactFromDb.getId());
        phoneNumberService.deletePhone(phone.getPhoneNumber());
        userService.deleteUser(user.getId());
    }
}
