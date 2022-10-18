package com.example.contactsApp.service;

import com.example.contactsApp.entity.History;
import com.example.contactsApp.entity.User;
import com.example.contactsApp.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return  userRepository.findAll();
    }

    @Override
    public void registerUser(User user) {
        userRepository.findUserByEmailOrUsernameForRegister(user.getEmail(), user.getUsername());

        userRepository.save(user);
    }

    @Override
    public User loginUser(User user) {
        User userOptional = userRepository.findUserByEmailOrUsername(user.getEmail(), user.getUsername());

        if(userOptional.getPassword().equals(user.getPassword()))
        return userOptional;

        throw new IllegalStateException();
    }

    @Override
    public User changePassword(User user) {
        User userOptional = userRepository.findUserByEmailOrUsername(user.getEmail(), user.getUsername());

        userOptional.setPassword(user.getPassword());

        userRepository.save(userOptional);

        return userOptional;
    }


}
