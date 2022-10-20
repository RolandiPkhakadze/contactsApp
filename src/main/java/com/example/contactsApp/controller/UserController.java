package com.example.contactsApp.controller;

import com.example.contactsApp.entity.User;
import com.example.contactsApp.service.userServices.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path  = "user")
public class UserController {

    private final UserService userService;

    @GetMapping(path  = "/get-all")
    public List<User> GetUser() {
        return userService.getAllUsers();
    }


    @PostMapping(path = "/register")
    public User registerUser(@Valid @RequestBody User user){
        return userService.registerUser(user);
    }

    @PutMapping(path = "/update-user/{id}")
    public User updateUser(@Valid  @RequestBody  User user,
                                     @PathVariable("id") Long id){
        user.setId(id);
        return userService.updateUser(user,id);
    }

    @PatchMapping(path = "/update-user/{id}")
    public User updateUserPartially(@RequestBody User user,
                                    @PathVariable("id") Long id){
        user.setId(id);
        return userService.updateUserPartially(user,id);
    }

    @PostMapping(path = "/login")
    public User loginUser(@RequestParam String usernameOrEmail,@RequestParam String password){
        return userService.loginUser(usernameOrEmail, password);
    }

    @PutMapping(path = "/change-password")
    public User changePassword(@RequestParam Long userId, @RequestParam String password){
        return userService.changePassword(userId, password);
    }


    @DeleteMapping(path = "/delete-user")
    public String deleteUser(@RequestParam Long userId){
        userService.deleteUser(userId);
        return "provider deleted";
    }
}
