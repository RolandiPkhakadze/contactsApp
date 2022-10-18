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

    @Query("SELECT u FROM User u WHERE u.email = ?1 OR u.username =?2")
    Optional<User> findUserByEmailOrUsername(String email,String username);

    Optional<User> getUserByUsernameAndPassword(String username, String password);

    Optional<User> findUserById(Long id);

}
