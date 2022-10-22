package com.example.contactsApp.controller;

import com.example.contactsApp.dtoConverter.converter.UserConverter;
import com.example.contactsApp.dtoConverter.dtoModel.UserDto;
import com.example.contactsApp.entity.User;
import com.example.contactsApp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path  = "user")
public class UserController {

    private final UserService userService;
    private final UserConverter converter;

    @GetMapping(path  = "/get-all")
    public List<UserDto> GetUser() {
        List<User> findAll = userService.getAllUsers();
        return converter.entityToDto(findAll);
    }


    @PostMapping(path = "/register")
    public UserDto registerUser(@Valid @RequestBody UserDto dto){
        User user = userService.registerUser(converter.dtoToEntity(dto));
        return converter.entityToDto(user);
    }

    @PutMapping(path = "/update-user/{id}")
    public UserDto updateUser(@Valid  @RequestBody  UserDto dto,
                                     @PathVariable("id") Long id){

        User user = userService.updateUser(converter.dtoToEntity(dto),id);
        return converter.entityToDto(user);
    }

    @PatchMapping(path = "/update-user/{id}")
    public UserDto updateUserPartially(@RequestBody UserDto dto,
                                    @PathVariable("id") Long id){

        User user = userService.updateUserPartially(converter.dtoToEntity(dto),id);
        return converter.entityToDto(user);
    }

    @PostMapping(path = "/login")
    public UserDto loginUser(@RequestParam String usernameOrEmail,@RequestParam String password){
        User user = userService.loginUser(usernameOrEmail, password);
        return converter.entityToDto(user);
    }

//    @PatchMapping(path = "/change-password")
//    public UserDto changePassword(@RequestParam Long userId, @RequestParam String password){
//        User user = userService.changePassword(userId, password);
//        return converter.entityToDto(user);
//    }


    @DeleteMapping(path = "/delete-user")
    public String deleteUser(@RequestParam Long userId){
        userService.deleteUser(userId);
        return "provider deleted";
    }
}
