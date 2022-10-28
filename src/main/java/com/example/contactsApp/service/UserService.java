package com.example.contactsApp.service;

import com.example.contactsApp.dto.UserDto;
import com.example.contactsApp.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserService {
    Page<UserDto> getAllUsers(Pageable pageable);

    User registerUser(User user);

    User loginUser(String usernameOrEmail, String password);

    User changePassword(Long userId, String password);

    void deleteUser(Long userId);

    User updateUser(User user, Long id);

    User updateUserPartially(User user, Long id);
}
