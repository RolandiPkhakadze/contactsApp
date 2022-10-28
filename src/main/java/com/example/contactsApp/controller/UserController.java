package com.example.contactsApp.controller;

import com.example.contactsApp.dto.UserDto;
import com.example.contactsApp.domain.User;
import com.example.contactsApp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(path  = "users")
public class UserController {

    private final UserService userService;

    // looks prettier, what do u think?
    @GetMapping
    public Page<UserDto> getAllUsers(Pageable pageable) {
        return userService.getAllUsers(pageable);
    }


    @PostMapping(path = "/register")
    public UserDto registerUser(@Valid @RequestBody UserDto dto){
        User user = userService.registerUser(converter.dtoToEntity(dto));
        return converter.entityToDto(user);
    }

    @PutMapping(path = "/{id}")
    public UserDto updateUser(@Valid  @RequestBody  UserDto dto,
                                     @PathVariable("id") Long id){

        User user = userService.updateUser(converter.dtoToEntity(dto),id);
        return converter.entityToDto(user);
    }

    @PatchMapping(path = "/{id}")
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


    @DeleteMapping
    public String deleteUser(@RequestParam Long userId){
        userService.deleteUser(userId);
        return "user deleted";
    }
}
