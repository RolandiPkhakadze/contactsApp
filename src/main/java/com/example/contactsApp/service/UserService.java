package com.example.contactsApp.service;

import com.example.contactsApp.entity.History;
import com.example.contactsApp.entity.User;
import com.example.contactsApp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return  userRepository.findAll();
    }

    public void registerUser(User user) {
        Optional <User> userOptional = userRepository.findUserByEmailOrUsername(user.getEmail(), user.getUsername());
        if(userOptional.isPresent()){
            throw new IllegalStateException();
        }

        userRepository.save(user);
    }

    public User loginUser(User user) {
        Optional <User> userOptional = userRepository.findUserByEmailOrUsername(user.getEmail(), user.getUsername()).
                                        filter(x -> x.getPassword().equals(user.getPassword()) );
        if(!userOptional.isPresent()){
            throw  new IllegalStateException();
        }

        return userOptional.get();
    }

    public User changePassword(User user) {
        Optional<User> userOptional = userRepository.findUserByEmailOrUsername(user.getEmail(), user.getUsername());
        if(!userOptional.isPresent()){
            throw  new IllegalStateException();
        }

        userOptional.get().setPassword(user.getPassword());

        userRepository.save(userOptional.get());

        return userOptional.get();
    }


}
