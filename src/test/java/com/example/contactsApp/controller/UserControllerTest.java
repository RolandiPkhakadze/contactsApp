package com.example.contactsApp.controller;

import com.example.contactsApp.dtoConverter.dtoModel.UserDto;
import com.example.contactsApp.entity.User;
import com.example.contactsApp.repository.UserRepository;
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
public class UserControllerTest {

    @Autowired
    private final UserController userController;
    @Autowired
    private final UserRepository userRepository;

    private static final String NAME = "testname";
    private static final String EMAIL = "test@mail.com";
    private static final String PASSWORD = "TestPassword12";
    private static final int USER_NUM_TO_ADD = 5;

    private User registerUser() {
        return registerUser(NAME, EMAIL, PASSWORD);
    }



    @Autowired
    public UserControllerTest(UserController userController, UserRepository userRepository) {
        this.userController = userController;
        this.userRepository = userRepository;
    }

    private User registerUser(String name, String email, String password) {
        var testUser = new User();
        testUser.setEmail(email);
        testUser.setUsername(name);
        testUser.setPassword(password);


        return userRepository.save(testUser);
    }

    private UserDto prepareUserDto() {
        return UserDto.builder()
                .email(EMAIL)
                .username(NAME)
                .password(PASSWORD)
                .build();
    }

    @Nested
    class GetAllUsersTest {
        @Test
        void getAllUserTest() {
            var testUsersIDs = addTestUsers().stream().map(User::getId).collect(Collectors.toList());

            var testUsersFromDbIDs = userController.getAllUsers().stream().map(UserDto::getId).toList();

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
            for(Long id : allUsersIDs) {
                userRepository.deleteById(id);
            }
        }
    }

    @Test
    void registerUserTest() {
        var userDto = prepareUserDto();
        var regUserDto = userController.registerUser(userDto);

        Assertions.assertEquals(userDto.getUsername(), regUserDto.getUsername());
        Assertions.assertEquals(userDto.getEmail(), regUserDto.getEmail());
        Assertions.assertEquals(userDto.getPassword(), regUserDto.getPassword());

        userRepository.deleteById(regUserDto.getId());

    }

    @Test
    void loginUserTest() {
        var userDto = prepareUserDto();
        var regUserDto = userController.registerUser(userDto);
        var loginUserDto = userController.loginUser(regUserDto.getEmail(), regUserDto.getPassword());

//        Assertions.assertEquals(userDto.getUsername(), loginUserDto.getUsername());
//        Assertions.assertEquals(userDto.getEmail(), loginUserDto.getEmail());
//        Assertions.assertEquals(userDto.getPassword(), loginUserDto.getPassword());

        Assertions.assertEquals(loginUserDto, regUserDto);

        userRepository.deleteById(regUserDto.getId());
    }

    @Test
    void deleteUserTest() {
        var userDto = prepareUserDto();
        var regUserDto = userController.registerUser(userDto);

        Assertions.assertEquals("user deleted", userController.deleteUser(regUserDto.getId()));

       // userRepository.deleteById(regUserDto.getId());
    }

    @Test
    void updateUserTest() {
        var userDto = prepareUserDto();
        var regUserDto = userController.registerUser(userDto);
        var updatedUserDto =
                UserDto.builder()
                        .email(EMAIL + 1)
                        .username(NAME + 1)
                        .password(PASSWORD + 1)
                        .build();

        var updatedUserFromController = userController.updateUser(updatedUserDto, regUserDto.getId());

        Assertions.assertEquals(updatedUserFromController.getUsername(), updatedUserDto.getUsername());
        Assertions.assertEquals(updatedUserFromController.getEmail(), updatedUserDto.getEmail());
        Assertions.assertEquals(updatedUserFromController.getPassword(), updatedUserDto.getPassword());


        userRepository.deleteById(regUserDto.getId());
    }



    @Test
    void updateUserPartially() {
        var userDto = prepareUserDto();
        var regUserDto = userController.registerUser(userDto);
        var updatedUserDto =
                UserDto.builder()
                        .username(NAME + 1)
                        .password(PASSWORD + 1)
                        .build();

        var updatedUserFromController = userController.updateUserPartially(updatedUserDto, regUserDto.getId());

        Assertions.assertEquals(updatedUserFromController.getUsername(), updatedUserDto.getUsername());
        Assertions.assertEquals(updatedUserFromController.getPassword(), updatedUserDto.getPassword());
        Assertions.assertEquals(regUserDto.getEmail(), updatedUserFromController.getEmail());

        userRepository.deleteById(regUserDto.getId());
    }



}
