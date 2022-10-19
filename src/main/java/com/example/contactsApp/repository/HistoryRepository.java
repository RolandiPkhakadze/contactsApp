package com.example.contactsApp.repository;

import com.example.contactsApp.entity.History;
import com.example.contactsApp.entity.PhoneNumber;
import com.example.contactsApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository  extends JpaRepository<History, Long> {

    List<History> getHistoriesByUser(User user);

}
