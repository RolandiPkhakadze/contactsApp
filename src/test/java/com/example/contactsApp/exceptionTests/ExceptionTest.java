package com.example.contactsApp.exceptionTests;

import com.example.contactsApp.Exception.*;
import com.example.contactsApp.entity.History;
import com.example.contactsApp.entity.NumberProvider;
import com.example.contactsApp.entity.PhoneNumber;
import com.example.contactsApp.entity.User;
import com.example.contactsApp.service.HistoryService;
import com.example.contactsApp.service.Impl.HistoryServiceImpl;
import com.example.contactsApp.service.PhoneNumberService;
import com.example.contactsApp.service.Impl.PhoneNumberServiceImpl;
import com.example.contactsApp.service.NumberProviderService;
import com.example.contactsApp.service.Impl.NumberProviderServiceImpl;
import com.example.contactsApp.service.Impl.UserServiceImpl;
import com.example.contactsApp.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;
import java.time.Month;

@SpringBootTest
@ActiveProfiles("test")
//@Testcontainers
public class ExceptionTest {
    /*@Container
    private static final PostgreSQLContainer container = (PostgreSQLContainer) new PostgreSQLContainer("postgres:latest").withReuse(true);

    @DynamicPropertySource
    public static void overrideProps(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url",container::getJdbcUrl);
        registry.add("spring.datasource.username",container::getUsername);
        registry.add("spring.datasource.password",container::getPassword);

    }*/
    private final UserService userService;
    private final PhoneNumberService phoneNumberService;
    private final NumberProviderService numberProviderService;
    private final HistoryService historyService;

    private static final String name = "testname";
    private static final String email = "test@mail.com";
    private static final String password = "TestPassword12";


    @Autowired
    public ExceptionTest(UserServiceImpl userService,
                         PhoneNumberServiceImpl phoneNumberService,
                         NumberProviderServiceImpl numberProviderService,
                         HistoryServiceImpl historyService)
    {
        this.userService = userService;
        this.phoneNumberService = phoneNumberService;
        this.numberProviderService = numberProviderService;
        this.historyService = historyService;
    }

    private User registerUser() {
        var testUser = new User();
        testUser.setEmail(email);
        testUser.setUsername(name);
        testUser.setPassword(password);

        return userService.registerUser(testUser);
    }

    @Test
    void userAlreadyExistExceptionTest() {
        var testUser = registerUser();

        Assertions.assertThrows(UserAlreadyExistsException.class, () -> userService.registerUser(testUser));

        userService.deleteUser(testUser.getId());

    }

    @Test
    void userDoesNotExistException() {
        Assertions.assertThrows(WrongEmailOrUsernameException.class, () -> userService.loginUser("asdfgsda", "Asdffgsd12asdf3"));
    }

    @Test
    void wrongPasswordExceptionTest() {
        var testUser = registerUser();

        Assertions.assertThrows(WrongPasswordException.class, () -> userService.loginUser(name, "wrong" + password));

        userService.deleteUser(testUser.getId());
    }

    @Test
    void phoneDoesNotExistExceptionTest() {
        Assertions.assertThrows(PhoneDoesNotExistException.class,() -> phoneNumberService.updatePhone(new PhoneNumber()));
    }

    @Test
    void providerDoesNotExistsExceptionTest() {
        Assertions.assertThrows(ProviderDoesNotExistException.class, () -> numberProviderService.updateProvider(new NumberProvider(), -1L));
    }

    @Test
    void callTimeExceptionTest() {
        var history = new History();
        var user = registerUser();
        history.setStartTime(LocalDateTime.of(2022, Month.OCTOBER, 20, 16, 9, 16));
        history.setEndTime(LocalDateTime.of(2022, Month.OCTOBER, 20, 16, 9, 8));

        Assertions.assertThrows(CallTimesException.class, () -> historyService.saveHistory(history, user.getId()));

        userService.deleteUser(user.getId());
    }

}
