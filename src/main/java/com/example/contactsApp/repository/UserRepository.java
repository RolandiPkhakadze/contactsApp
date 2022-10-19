package com.example.contactsApp.repository;

import com.example.contactsApp.Exception.UserAlreadyExistsException;
import com.example.contactsApp.Exception.UserDoesNotExistException;
import com.example.contactsApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

    default User getUserById(Long id) {
        return findUserById(id).orElseThrow(() -> new UserDoesNotExistException("user with id: "+id+" was not found."));
    }


    default User findUserByEmailOrUsername(String email,String username){
        return getUserByEmailOrUsername(email,username).orElseThrow(() -> new UserDoesNotExistException("user was not found."));
    }

    default void findUserByEmailOrUsernameForRegister(String email,String username){

        if(getUserByEmailOrUsername(email,username).isPresent()){
            throw new UserAlreadyExistsException("user with same email or username is already registered.");
        }
    }

    @Query("SELECT u FROM User u WHERE u.email = ?1 OR u.username =?2")
    Optional<User> getUserByEmailOrUsername(String email,String username);

    Optional<User> findUserById(Long id);

}
