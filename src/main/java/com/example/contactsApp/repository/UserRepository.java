package com.example.contactsApp.repository;

import com.example.contactsApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

    default User getUserById(Long id) {
        return findUserById(id).orElseThrow(() -> new RuntimeException("dsf"));
    }


    default User findUserByEmailOrUsername(String email,String username){
        return getUserByEmailOrUsername(email,username).orElseThrow(() -> new RuntimeException("dsf"));
    }

    default void findUserByEmailOrUsernameForRegister(String email,String username){

        if(getUserByEmailOrUsername(email,username).isPresent()){
            new RuntimeException("dsf");
        }
    }

    @Query("SELECT u FROM User u WHERE u.email = ?1 OR u.username =?2")
    Optional<User> getUserByEmailOrUsername(String email,String username);

    Optional<User> findUserById(Long id);

}
