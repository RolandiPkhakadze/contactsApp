package com.example.contactsApp.service.Impl;

import com.example.contactsApp.exceptions.WrongPasswordException;
import com.example.contactsApp.converter.UserConverter;
import com.example.contactsApp.dto.UserDto;
import com.example.contactsApp.domain.User;
import com.example.contactsApp.repository.UserRepository;
import com.example.contactsApp.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    static final int PAGINATION_SIZE = 5;
    private CustomMapper mapper;

    private final UserConverter converter;
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    // here u need to return not a domain entity but user dto
    // as u can see here u can use pageable on service layer and chain it to repository level.
    public Page<UserDto> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(converter::entityToDto);
    }

    @Transactional
    @Override
    public User registerUser(User user) {
        userRepository.findUserByEmailOrUsernameForRegister(user.getEmail(), user.getUsername());
        user = userRepository.save(user);
        log.debug("User registered with id "+user.getId());
        return user;
    }

    @Override
    public User loginUser(String usernameOrEmail, String password) {
        User userOptional = userRepository.findUserByEmailOrUsername(usernameOrEmail,usernameOrEmail);
        if(!userOptional.getPassword().equals(password)) {
            log.error("wrong password try again");
            throw new WrongPasswordException("wrong password try again");
        }

        log.debug(String.format("user with id: %d signed in",userOptional.getId()));

        return userOptional;
    }

    @Transactional
    @Override
    public User changePassword(Long userId, String password) {
        User userOptional = userRepository.getUserById(userId);

        userOptional.setPassword(password);

        userRepository.save(userOptional);

        log.debug(String.format("user with id: %d changed his/her password",userId));
        return userOptional;
    }


    @Transactional
    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
        log.debug(String.format("user with id: %d was deleted",userId));
    }

    @Transactional
    @Override
    public User updateUser(@Valid User user, Long id) {
        userRepository.getUserById(id);
        user.setId(id);
        user = userRepository.save(user);

        log.debug(String.format("user with id: %d was updated",id));
        return user;
    }

    @Transactional
    @Override
    public User updateUserPartially(User user, Long id) {
        User userForSave = userRepository.getUserById(id);
        user = userRepository.save(mapper.userNullExclude(userForSave,user));

        log.debug(String.format("user with id: %d was updated",id));
        return user;
    }


}
