package com.example.contactsApp.dtoConverter.converter;

import com.example.contactsApp.dtoConverter.dtoModel.UserDto;
import com.example.contactsApp.entity.User;
import com.example.contactsApp.service.Impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class UserConverterTest {

    private final UserConverter userConverter;
    private final UserServiceImpl userService;
    private static final String NAME = "testname";
    private static final String EMAIL = "test@mail.com";
    private static final String PASSWORD = "TestPassword12";

    private static final int LIST_SIZE = 5;


    @Autowired
    public UserConverterTest(UserConverter userConverter, UserServiceImpl userService) {
        this.userConverter = userConverter;
        this.userService = userService;
    }

    private User registerUser() {
        var testUser = new User();
        testUser.setEmail(EMAIL);
        testUser.setUsername(NAME);
        testUser.setPassword(PASSWORD);

        return userService.registerUser(testUser);
    }

    private User registerUser(String email, String name, String password) {
        var testUser = new User();
        testUser.setEmail(email);
        testUser.setUsername(name);
        testUser.setPassword(password);

        return userService.registerUser(testUser);
    }


    private List<User> generateListOfUsers() {
        List<User> result = new ArrayList<>();
        for(int i = 0; i < LIST_SIZE; i++) {
            result.add(registerUser(EMAIL + i, NAME + i, PASSWORD + i));
        }
        return result;
    }

    private UserDto initDto() {
        return UserDto.builder()
                .username(NAME)
                .email(EMAIL)
                .password(PASSWORD)
                .build();
    }



    @Test
    void entityToDtoTest() {
        var user = registerUser();
        var userDto = userConverter.entityToDto(user);

        checkAssertionsEntityToDto(user, userDto);


    }



    @Test
    void entityToDtoListTest() {

        var usersList = generateListOfUsers();
        var userDtoList = userConverter.entityToDto(usersList);

        for(int i = 0; i < LIST_SIZE; i++) {
            var user = usersList.get(i);
            var userDto = userDtoList.get(i);

            checkAssertionsEntityToDto(user, userDto);

        }



    }

    @Test
    void dtoToEntityTest() {
        var userDto = initDto();
        var user = userConverter.dtoToEntity(userDto);

        checkAssertionsDtoToEntity(user, userDto);

    }

    private void checkAssertionsEntityToDto(User user, UserDto userDto) {
        Assertions.assertEquals(user.getId(), userDto.getId());
        checkAssertionsDtoToEntity(user, userDto);
        userService.deleteUser(user.getId());
    }

    private void checkAssertionsDtoToEntity( User user, UserDto userDto) {
        Assertions.assertEquals(user.getUsername(), userDto.getUsername());
        Assertions.assertEquals(user.getPassword(), userDto.getPassword());
        Assertions.assertEquals(user.getEmail(), userDto.getEmail());
    }


}
