package com.example.contactsApp.repository;

import com.example.contactsApp.Exception.HistoryDoesNotExistException;
import com.example.contactsApp.entity.History;
import com.example.contactsApp.entity.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HistoryRepository  extends JpaRepository<History, Long> {

    List<History> getHistoriesByUser(User user, PageRequest pageRequest);

    default History getHistoriesById(Long id) {
        return findHistoriesById(id).orElseThrow(() -> new HistoryDoesNotExistException("provider with id: "+id+" was not found."));
    }

    Optional<History> findHistoriesById(Long id);
}
