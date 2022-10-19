package com.example.contactsApp.service;

import com.example.contactsApp.entity.User;

import java.util.List;


public interface UserService {
    List<User> getAllUsers();

    void registerUser(User user);

    User loginUser(User user);

    User changePassword(Long userId, String password);

    void deletePhone(Long userId);
}
