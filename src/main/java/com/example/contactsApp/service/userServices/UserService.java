package com.example.contactsApp.service.userServices;

import com.example.contactsApp.entity.User;

import java.util.List;


public interface UserService {
    List<User> getAllUsers();

    User registerUser(User user);

    User loginUser(String usernameOrEmail, String password);

    User changePassword(Long userId, String password);

    void deletePhone(Long userId);
}
