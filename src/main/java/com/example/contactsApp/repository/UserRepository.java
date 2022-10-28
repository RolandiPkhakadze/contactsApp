package com.example.contactsApp.repository;

import com.example.contactsApp.exceptions.UserAlreadyExistsException;
import com.example.contactsApp.exceptions.UserDoesNotExistException;
import com.example.contactsApp.exceptions.WrongEmailOrUsernameException;
import com.example.contactsApp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

    default User getUserById(Long id) {
        return findUserById(id).orElseThrow(() -> new UserDoesNotExistException(String.format("user with id: %d was not found.", id)));
    }


    default User findUserByEmailOrUsername(String email,String username){
        return getUserByEmailOrUsername(email,username)
                .orElseThrow(() -> new WrongEmailOrUsernameException(String.format("user %s was not found.", email.equals("")?username:email)));
    }

    default void findUserByEmailOrUsernameForRegister(String email,String username){

        if(getUserByEmailOrUsername(email,username).isPresent()){
            throw new UserAlreadyExistsException(String.format("user %s already exists.",email.equals("")?username:email));
        }
    }

    @Query("SELECT u FROM User u WHERE u.email = ?1 OR u.username =?2")
    Optional<User> getUserByEmailOrUsername(String email,String username);

    Optional<User> findUserById(Long id);

}
