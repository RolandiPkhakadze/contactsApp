package com.example.contactsApp.controller;

import com.example.contactsApp.entity.User;
import com.example.contactsApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path  = "user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path  = "Getall")
    @GetMapping
    public List<User> GetUser() {
        return userService.GetAllUsers();
    }

    @RequestMapping(path = "register")
    @PostMapping
    public void registerUser(@RequestBody User user){
        userService.registerUser(user);
    }

}
