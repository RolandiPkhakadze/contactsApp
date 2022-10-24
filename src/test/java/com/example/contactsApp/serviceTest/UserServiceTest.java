package com.example.contactsApp.serviceTest;

import com.example.contactsApp.Exception.UserDoesNotExistException;
import com.example.contactsApp.Exception.WrongPasswordException;
import com.example.contactsApp.entity.User;
import com.example.contactsApp.service.Impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
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
    void loginWrongPasswordTest() {
        var testUser = registerUser();

        Assertions.assertThrows(WrongPasswordException.class, () -> userService.loginUser(name, String.format("wrong%s", password)));

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

    @Test
    void updateUserTest() {
        var testUser = registerUser();
        var newName = String.format("new%s", name);
        var newPassword = String.format("new%s",password);
        var newEmail = String.format("new%s",email);
        testUser.setPassword(newPassword);
        testUser.setUsername(newName);
        testUser.setEmail(newEmail);

        var updatedUser = userService.updateUser(testUser, testUser.getId());

        Assertions.assertEquals(updatedUser.getEmail(), newEmail);
        Assertions.assertEquals(updatedUser.getUsername(),newName);
        Assertions.assertEquals(updatedUser.getPassword(), newPassword);

        userService.deleteUser(testUser.getId());
    }



    @Nested
    class updateUserPartially {
        @Test
        void updateUserPartiallyUserNameTest() {
            var testUser = registerUser();
            var newName = String.format("new%s", name);
            testUser.setUsername(newName);

            var updatedUser = userService.updateUserPartially(testUser, testUser.getId());

            Assertions.assertEquals(updatedUser.getUsername(),newName);

            userService.deleteUser(testUser.getId());
        }

        @Test
        void updateUserPartiallyEmailTest() {
            var testUser = registerUser();
            var newEmail = String.format("new%s",email);
            testUser.setEmail(newEmail);

            var updatedUser = userService.updateUserPartially(testUser, testUser.getId());

            Assertions.assertEquals(updatedUser.getEmail(), newEmail);

            userService.deleteUser(testUser.getId());
        }

        @Test
        void updateUserPartiallyPasswordTest() {
            var testUser = registerUser();
            var newPassword = String.format("new%s",password);
            testUser.setPassword(newPassword);

            var updatedUser = userService.updateUserPartially(testUser, testUser.getId());

            Assertions.assertEquals(updatedUser.getPassword(), newPassword);

            userService.deleteUser(testUser.getId());
        }

        @Test
        void updateUserPartiallyUserNameAndPasswordTest() {
            var testUser = registerUser();
            var newName = String.format("new%s", name);
            var newPassword = String.format("new%s",password);
            testUser.setPassword(newPassword);
            testUser.setUsername(newName);

            var updatedUser = userService.updateUserPartially(testUser, testUser.getId());

            Assertions.assertEquals(updatedUser.getUsername(),newName);
            Assertions.assertEquals(updatedUser.getPassword(), newPassword);

            userService.deleteUser(testUser.getId());
        }
        @Test
        void updateUserPartiallyUserNameAndEmailTest() {
            var testUser = registerUser();
            var newName = String.format("new%s", name);
            var newEmail = String.format("new%s",email);
            testUser.setUsername(newName);
            testUser.setEmail(newEmail);

            var updatedUser = userService.updateUserPartially(testUser, testUser.getId());

            Assertions.assertEquals(updatedUser.getEmail(), newEmail);
            Assertions.assertEquals(updatedUser.getUsername(),newName);

            userService.deleteUser(testUser.getId());
        }

        @Test
        void updateUserPartiallyEmailAndPasswordTest() {
            var testUser = registerUser();
            var newPassword = String.format("new%s",password);
            var newEmail = String.format("new%s",email);
            testUser.setPassword(newPassword);
            testUser.setEmail(newEmail);

            var updatedUser = userService.updateUserPartially(testUser, testUser.getId());

            Assertions.assertEquals(updatedUser.getEmail(), newEmail);
            Assertions.assertEquals(updatedUser.getPassword(), newPassword);

            userService.deleteUser(testUser.getId());
        }

        @Test
        void updateUserPartiallyEverythingTest() {
            var testUser = registerUser();
            var newName = String.format("new%s", name);
            var newPassword = String.format("new%s",password);
            var newEmail = String.format("new%s",email);
            testUser.setPassword(newPassword);
            testUser.setUsername(newName);
            testUser.setEmail(newEmail);

            var updatedUser = userService.updateUserPartially(testUser, testUser.getId());

            Assertions.assertEquals(updatedUser.getEmail(), newEmail);
            Assertions.assertEquals(updatedUser.getUsername(),newName);
            Assertions.assertEquals(updatedUser.getPassword(), newPassword);

            userService.deleteUser(testUser.getId());
        }

    }

}
