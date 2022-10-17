package com.example.contactsApp.controller;

import com.example.contactsApp.entity.User;
import com.example.contactsApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public User createUser(final User user) {
        return userService.createUser(user);
    }



}
