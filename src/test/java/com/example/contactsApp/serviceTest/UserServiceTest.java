package com.example.contactsApp.serviceTest;

import com.example.contactsApp.Exception.UserDoesNotExistException;
import com.example.contactsApp.entity.User;
import com.example.contactsApp.service.userServices.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {


    private final UserServiceImpl userService;
    private static final String name = "testname";
    private static final String email = "test@mail.com";
    private static final String password = "TestPassword12";


    @Autowired
    public UserServiceTest(UserServiceImpl userService) {
        this.userService = userService;
    }

    private User registerUser() {
        var testUser = new User();
        testUser.setEmail(email);
        testUser.setUsername(name);
        testUser.setPassword(password);

        return userService.registerUser(testUser);
    }

    @Test
    void deleteUserTest() {
        var testUser = registerUser();
        var id = testUser.getId();
        userService.deleteUser(id);

        Assertions.assertThrows(UserDoesNotExistException.class, () -> userService.loginUser(testUser.getUsername(), testUser.getPassword()));
    }

    @Test
    void registerUserTest() {
        var result = registerUser();
        userService.deleteUser(result.getId());

        Assertions.assertNotNull(result);
    }

    @Test
    void loginTest() {
        var testUser = registerUser();
        var loginUser = userService.loginUser(testUser.getUsername(), testUser.getPassword());

        Assertions.assertEquals(testUser.getId(), loginUser.getId());

        userService.deleteUser(testUser.getId());
    }

    @Test
    void changePasswordTest() {

        var testUser = registerUser();
        var expected = "NewTestPass12";
        userService.changePassword(testUser.getId(), expected);

        Assertions.assertNotNull(userService.loginUser(name, expected));

        userService.deleteUser(testUser.getId());
    }

}
