package com.example.contactsApp.service;


import com.example.contactsApp.entity.History;
import com.example.contactsApp.repository.HistoryRepository;
import com.example.contactsApp.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class HistoryServiceImpl implements HistoryService {
    private final HistoryRepository historyRepository;
    private final UserRepository userRepository;

    @Override
    public History saveHistory(History history, Long userId) {
        history.setUser(userRepository.getUserById(userId));
        return historyRepository.save(history);
    }

    @Override
    public List<History> getAllHistoriesForUser(Long userId) {
        return historyRepository.getHistoriesByUser(userRepository.getUserById(userId));
    }

    @Override
    public void deleteHistory(Long historyId) {
        historyRepository.deleteById(historyId);
    }
}
