package com.example.contactsApp.controller;

import com.example.contactsApp.entity.User;
import com.example.contactsApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path  = "user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path  = "/get-all")
    public List<User> GetUser() {
        return userService.getAllUsers();
    }


    @PutMapping(path = "/register")
    public String registerUser(@Valid @RequestBody User user){
        userService.registerUser(user);
        return "User registered";
    }

    @PostMapping(path = "/login")
    public User loginUser(@Valid @RequestBody User user){
        return userService.loginUser(user);
    }

    @PostMapping(path = "/change-password")
    public User changePassword(@RequestParam Long userId, @RequestParam String password){
        return userService.changePassword(userId, password);
    }


    @DeleteMapping(path = "/delete-user")
    public String deleteUser(@RequestParam Long userId){
        userService.deletePhone(userId);
        return "provider deleted";
    }
}
