package com.example.contactsApp.service.Impl;


import com.example.contactsApp.Exception.CallTimesException;
import com.example.contactsApp.entity.History;
import com.example.contactsApp.repository.HistoryRepository;
import com.example.contactsApp.repository.UserRepository;
import com.example.contactsApp.service.Intf.HistoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class HistoryServiceImpl implements HistoryService {
    private final HistoryRepository historyRepository;
    private final UserRepository userRepository;

    @Override
    public History saveHistory(History history, Long userId) {
        if(history.getStartTime().isAfter(history.getEndTime())){
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

    @Override
    public History updateHistory(History history, Long id) {
        if(history.getStartTime().isAfter(history.getEndTime())){
            throw new CallTimesException("call's start time should be less than end time");
        }

        historyRepository.getHistoriesById(id);
        history.setId(id);

        return historyRepository.save(history);
    }

    @Override
    public History updateHistoryPartially(History history, Long id) {
        History historyForSave = historyRepository.getHistoriesById(id);

        LocalDateTime startDate = history.getStartTime();
        historyForSave.setStartTime(startDate !=null? startDate :historyForSave.getStartTime());
        LocalDateTime endDate = history.getEndTime();
        historyForSave.setEndTime(endDate !=null? endDate :historyForSave.getEndTime());
        return historyRepository.save(historyForSave);
    }
}
