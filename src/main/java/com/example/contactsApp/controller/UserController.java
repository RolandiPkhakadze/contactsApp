package com.example.contactsApp.controller;

import com.example.contactsApp.entity.Contact;
import com.example.contactsApp.entity.User;
import com.example.contactsApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(path  = "get_all")
    public List<User> GetUser() {
        return userService.getAllUsers();
    }


    @PutMapping(path = "register")
    public String registerUser(@RequestBody User user){
        userService.registerUser(user);
        return "User registered";
    }

    @PostMapping(path = "login")
    public User loginUser(@RequestBody User user){
        return userService.loginUser(user);
    }

    @PostMapping(path = "change_password")
    public User changePassword(@RequestBody User user){
        return userService.changePassword(user);
    }


}
