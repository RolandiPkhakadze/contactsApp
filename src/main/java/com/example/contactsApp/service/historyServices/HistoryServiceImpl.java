package com.example.contactsApp.service.historyServices;


import com.example.contactsApp.Exception.CallTimesException;
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
        if(history.getStartDate().isAfter(history.getEndDate())){
            log.debug("Call Times exception was thrown");
            throw new CallTimesException("call's start time should be less than end time");
        }
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
