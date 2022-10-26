package com.example.contactsApp.service.Impl;


import com.example.contactsApp.Exception.CallTimesException;
import com.example.contactsApp.Exception.HistoryDoesNotExistException;
import com.example.contactsApp.entity.History;
import com.example.contactsApp.repository.HistoryRepository;
import com.example.contactsApp.repository.UserRepository;
import com.example.contactsApp.service.HistoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class HistoryServiceImpl implements HistoryService {
    private final HistoryRepository historyRepository;
    private final UserRepository userRepository;
    private final CustomMapper mapper;

    private static final int PAGINATION_PAGE = 0;
    private static final int PAGINATION_SIZE = 2;

    @Transactional
    @Override
    public History saveHistory(History history, Long userId) {
        if(history.getStartTime().isAfter(history.getEndTime())){
        //    log.debug("Call Times exception was thrown");
            throw new CallTimesException(String.format("call start time: %s must be less than end time: %s",
                    history.getStartTime().toString(),history.getEndTime().toString()));
        }

        history.setUser(userRepository.getUserById(userId));

        History savedHistory = historyRepository.save(history);
        log.debug("history: " +savedHistory + "was saved.");
        return savedHistory;
    }

    @Override
    @Transactional(readOnly = true)
    public List<History> getAllHistoriesForUser(Long userId) {
        return historyRepository.getHistoriesByUser(userRepository.getUserById(userId), PageRequest.of(PAGINATION_PAGE,PAGINATION_SIZE));
    }

    @Transactional
    @Override
    public void deleteHistory(Long historyId) {
        try{
            historyRepository.deleteById(historyId);
            log.debug(String.format("history with id %d was deleted",historyId));
        }catch(EmptyResultDataAccessException ex) {
            throw new HistoryDoesNotExistException(String.format("history with id: %d was not found.",historyId));
        }
    }

    @Transactional
    @Override
    public History updateHistory(History history, Long id) {
        if(history.getStartTime().isAfter(history.getEndTime())){
            throw new CallTimesException(String.format("call start time: %s must be less than end time: %s",
                    history.getStartTime().toString(),history.getEndTime().toString()));
        }

        historyRepository.getHistoriesById(id);
        history.setId(id);

        History savedHistory = historyRepository.save(history);
        log.debug(String.format("history with id %d was updated",id));
        return savedHistory;
    }

    @Transactional
    @Override
    public History updateHistoryPartially(History history, Long id) {
        History historyForSave = historyRepository.getHistoriesById(id);
        History savedHistory = historyRepository.save(mapper.historyNullExclude(historyForSave,history));
        log.debug(String.format("history with id %d was updated",id));
        return savedHistory;
    }
}
