package com.example.contactsApp.service.Impl;

import com.example.contactsApp.Exception.WrongEmailOrUsernameException;
import com.example.contactsApp.Exception.WrongPasswordException;
import com.example.contactsApp.entity.User;
import com.example.contactsApp.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@ActiveProfiles("test")

class UserServiceTest {
    /*@Container
    private static final PostgreSQLContainer container = (PostgreSQLContainer) new PostgreSQLContainer("postgres:latest").withReuse(true);

    @DynamicPropertySource
    public static void overrideProps(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url",container::getJdbcUrl);
        registry.add("spring.datasource.username",container::getUsername);
        registry.add("spring.datasource.password",container::getPassword);

    }*/

    @Autowired
    private UserService userService;
    private static final String NAME = "testname";
    private static final String EMAIL = "test@mail.com";
    private static final String PASSWORD = "TestPassword12";
    private static final int USER_NUM_TO_ADD = 5;

    private User registerUser() {
        return registerUser(NAME, EMAIL, PASSWORD);
    }

    private User registerUser(String name, String email, String password) {
        var testUser = new User();
        testUser.setEmail(email);
        testUser.setUsername(name);
        testUser.setPassword(password);


        return userService.registerUser(testUser);
    }

    @Test
    void deleteUserTest() {
        var testUser = registerUser();
        userService.deleteUser(testUser.getId());
        Assertions.assertThrows(WrongEmailOrUsernameException.class, () -> userService.loginUser(testUser.getUsername(), testUser.getPassword()));
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

        Assertions.assertThrows(WrongPasswordException.class, () -> userService.loginUser(NAME, String.format("wrong%s", PASSWORD)));

        userService.deleteUser(testUser.getId());
    }

    @Test
    void changePasswordTest() {

        var testUser = registerUser();
        var expected = "NewTestPass12";
        userService.changePassword(testUser.getId(), expected);

        Assertions.assertNotNull(userService.loginUser(NAME, expected));

        userService.deleteUser(testUser.getId());
    }

    @Test
    void updateUserTest() {
        var testUser = registerUser();
        var newName = String.format("new%s", NAME);
        var newPassword = String.format("new%s", PASSWORD);
        var newEmail = String.format("new%s", EMAIL);
        testUser.setPassword(newPassword);
        testUser.setUsername(newName);
        testUser.setEmail(newEmail);

        var updatedUser = userService.updateUser(testUser, testUser.getId());

        Assertions.assertEquals(updatedUser.getEmail(), newEmail);
        Assertions.assertEquals(updatedUser.getUsername(),newName);
        Assertions.assertEquals(updatedUser.getPassword(), newPassword);

        userService.deleteUser(testUser.getId());
    }


    @Test
    void getAllUsersTest() {
        var allUsersIDs = userService.getAllUsers().stream().map(User::getId).collect(Collectors.toList());
        removeAllUsersFromDB(allUsersIDs);

        var testUsersIDs = addTestUsers().stream().map(User::getId).collect(Collectors.toList());

        var testUsersFromDbIDs = userService.getAllUsers().stream().map(User::getId).toList();

        System.out.println(testUsersIDs);
        System.out.println(testUsersFromDbIDs);

        for(Long id : testUsersFromDbIDs) {
            Assertions.assertTrue(testUsersIDs.contains(id));
        }

        removeAllUsersFromDB(testUsersIDs);

    }

    private List<User> addTestUsers() {
        List<User> result = new ArrayList<>();
        for(int i = 0; i < USER_NUM_TO_ADD; i++) {
            result.add(registerUser(NAME + i, EMAIL + i, PASSWORD + i));
        }
        return result;
    }

    private void removeAllUsersFromDB(List<Long> allUsersIDs) {
        System.out.println(allUsersIDs);
        for(Long id : allUsersIDs) {
            userService.deleteUser(id);
        }
    }


    @Nested
    class updateUserPartially {
        @Test
        void updateUserPartiallyUserNameTest() {
            var testUser = registerUser();
            var newName = String.format("new%s", NAME);
            testUser.setUsername(newName);

            var updatedUser = userService.updateUserPartially(testUser, testUser.getId());

            Assertions.assertEquals(updatedUser.getUsername(),newName);

            userService.deleteUser(testUser.getId());
        }

        @Test
        void updateUserPartiallyEmailTest() {
            var testUser = registerUser();
            var newEmail = String.format("new%s", EMAIL);
            testUser.setEmail(newEmail);

            var updatedUser = userService.updateUserPartially(testUser, testUser.getId());

            Assertions.assertEquals(updatedUser.getEmail(), newEmail);

            userService.deleteUser(testUser.getId());
        }

        @Test
        void updateUserPartiallyPasswordTest() {
            var testUser = registerUser();
            var newPassword = String.format("new%s", PASSWORD);
            testUser.setPassword(newPassword);

            var updatedUser = userService.updateUserPartially(testUser, testUser.getId());

            Assertions.assertEquals(updatedUser.getPassword(), newPassword);

            userService.deleteUser(testUser.getId());
        }

        @Test
        void updateUserPartiallyUserNameAndPasswordTest() {
            var testUser = registerUser();
            var newName = String.format("new%s", NAME);
            var newPassword = String.format("new%s", PASSWORD);
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
            var newName = String.format("new%s", NAME);
            var newEmail = String.format("new%s", EMAIL);
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
            var newPassword = String.format("new%s", PASSWORD);
            var newEmail = String.format("new%s", EMAIL);
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
            var newName = String.format("new%s", NAME);
            var newPassword = String.format("new%s", PASSWORD);
            var newEmail = String.format("new%s", EMAIL);
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
