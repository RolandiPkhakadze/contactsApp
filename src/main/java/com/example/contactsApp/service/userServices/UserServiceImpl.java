package com.example.contactsApp.service.userServices;

import com.example.contactsApp.Exception.WrongPasswordException;
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
    public User registerUser(User user) {
        userRepository.findUserByEmailOrUsernameForRegister(user.getEmail(), user.getUsername());
        return userRepository.save(user);
    }

    @Override
    public User loginUser(String usernameOrEmail, String password) {
        User userOptional = userRepository.findUserByEmailOrUsername(usernameOrEmail,usernameOrEmail);
        if(!userOptional.getPassword().equals(password)) {
            throw new WrongPasswordException("wrong password try again");
        }

        return userOptional;
    }

    @Override
    public User changePassword(Long userId, String password) {
        User userOptional = userRepository.getUserById(userId);

        userOptional.setPassword(password);

        userRepository.save(userOptional);

        return userOptional;
    }


    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User updateUser(User user, Long id) {
        user.setId(id);
        userRepository.getUserById(id);
        return userRepository.save(user);
    }

    @Override
    public User updateUserPartially(User user, Long id) {
        User userForSave = userRepository.getUserById(id);
        userForSave.setEmail(user.getEmail()!=null? user.getEmail() : userForSave.getEmail());
        userForSave.setPassword(user.getPassword()!=null? user.getPassword(): userForSave.getPassword());
        userForSave.setUsername(user.getUsername()!=null? user.getUsername() : userForSave.getUsername());
        return userRepository.save(userForSave);
    }


}
