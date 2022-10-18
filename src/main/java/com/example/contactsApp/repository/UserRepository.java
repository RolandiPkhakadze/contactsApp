package com.example.contactsApp.repository;

import com.example.contactsApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

    @Query("Select u From User u where u.id = ?1,")
    Optional<User> getUserById(Long id);

    @Query("SELECT u FROM User u WHERE u.email = ?1 OR u.username =?2")
    Optional<User> findUserByEmailOrUsername(String email,String username);

    Optional<User> getUserByUsernameAndPassword(String username, String password);

}
