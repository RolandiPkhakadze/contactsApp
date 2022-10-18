package com.example.contactsApp.service;

import com.example.contactsApp.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List<User> getAllUsers();

    public void registerUser(User user);

    public User loginUser(User user);

    public User changePassword(User user);
}
