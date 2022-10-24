package com.example.contactsApp.repository;

import com.example.contactsApp.Exception.UserAlreadyExistsException;
import com.example.contactsApp.Exception.UserDoesNotExistException;
import com.example.contactsApp.Exception.WrongEmailOrUsernameException;
import com.example.contactsApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

    default User getUserById(Long id) {
        return findUserById(id).orElseThrow(() -> new UserDoesNotExistException(id));
    }


    default User findUserByEmailOrUsername(String email,String username){
        return getUserByEmailOrUsername(email,username)
                .orElseThrow(() -> new WrongEmailOrUsernameException(email.equals("")?username:email));
    }

    default void findUserByEmailOrUsernameForRegister(String email,String username){

        if(getUserByEmailOrUsername(email,username).isPresent()){
            throw new UserAlreadyExistsException(email.equals("")?username:email);
        }
    }

    @Query("SELECT u FROM User u WHERE u.email = ?1 OR u.username =?2")
    Optional<User> getUserByEmailOrUsername(String email,String username);

    Optional<User> findUserById(Long id);

}
