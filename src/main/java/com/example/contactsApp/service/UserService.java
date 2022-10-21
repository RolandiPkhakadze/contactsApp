package com.example.contactsApp.service;

import com.example.contactsApp.entity.User;

import java.util.List;


public interface UserService {
    List<User> getAllUsers();

    User registerUser(User user);

    User loginUser(String usernameOrEmail, String password);

    User changePassword(Long userId, String password);

    void deleteUser(Long userId);

    User updateUser(User user, Long id);

    User updateUserPartially(User user, Long id);
}
