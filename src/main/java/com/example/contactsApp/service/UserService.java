package com.example.contactsApp.service;

import com.example.contactsApp.entity.History;
import com.example.contactsApp.entity.User;
import com.example.contactsApp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> GetUsers(){
        return  userRepository.findAll();
    }
}
