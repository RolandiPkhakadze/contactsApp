package com.example.contactsApp.service.Impl;

import com.example.contactsApp.Exception.UserDoesNotExistException;
import com.example.contactsApp.Exception.WrongPasswordException;
import com.example.contactsApp.entity.User;
import com.example.contactsApp.repository.UserRepository;
import com.example.contactsApp.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private CustomMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return  userRepository.findAll(PageRequest.of(0,2)).stream().toList();
    }

    @Transactional(rollbackFor = UserDoesNotExistException.class)
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

    @Transactional(rollbackFor = UserDoesNotExistException.class)
    @Override
    public User changePassword(Long userId, String password) {
        User userOptional = userRepository.getUserById(userId);

        userOptional.setPassword(password);

        userRepository.save(userOptional);

        return userOptional;
    }


    @Transactional(rollbackFor = UserDoesNotExistException.class)
    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Transactional(rollbackFor = UserDoesNotExistException.class)
    @Override
    public User updateUser(@Valid User user, Long id) {
        userRepository.getUserById(id);
        user.setId(id);
        return userRepository.save(user);
    }
//mappers, mapstruct, objectMapper
    @Transactional(rollbackFor = UserDoesNotExistException.class)
    @Override
    public User updateUserPartially(User user, Long id) {
        User userForSave = userRepository.getUserById(id);
        return userRepository.save(mapper.userNullExclude(userForSave,user));
    }


}
