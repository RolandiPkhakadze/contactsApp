package com.example.contactsApp.service.Impl;

import com.example.contactsApp.Exception.CallTimesException;
import com.example.contactsApp.Exception.HistoryDoesNotExistException;
import com.example.contactsApp.entity.History;
import com.example.contactsApp.entity.User;
import com.example.contactsApp.repository.HistoryRepository;
import com.example.contactsApp.repository.UserRepository;
import com.example.contactsApp.service.HistoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.Duration;
import java.time.LocalDateTime;

@SpringBootTest
@ActiveProfiles("test")
public class HistoryServiceTest {

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private HistoryService historyService;
    @Autowired
    private UserRepository userRepository;

    private static final LocalDateTime startTimer = LocalDateTime.now().minusMinutes(2);
    private static final LocalDateTime endTimer = LocalDateTime.now();

    public static User getUser(){
        return User.builder()
                .email("test@user.user")
                .password("PassdsERQSaasdW")
                .username("qwert")
                .build();
    }


    public static History getHistory(){

        return History.builder()
                .endTime(endTimer)
                .startTime(startTimer)
                .duration(Duration.between(startTimer,endTimer).toSeconds())
                .build();
    }

    @Test
    void saveHistoryWithTimesExceptionTest() {
        User user = userRepository.save( getUser());

        History history =getHistory();
        history.setStartTime(endTimer.plusHours(3));

        Assertions.assertThrows(CallTimesException.class,
                () -> historyService.saveHistory(history, user.getId()));

        userRepository.deleteById(user.getId());
    }

    @Test
    void saveHistoryTest() {
        User user = userRepository.save( getUser());

        History history = historyService.saveHistory(getHistory(),user.getId());
        History historyFromBase = historyRepository.getHistoriesById(history.getId());

        Assertions.assertEquals(history.toString(),historyFromBase.toString());

        historyRepository.deleteById(history.getId());
        userRepository.deleteById(user.getId());
    }

    @Test
    void deleteHistoryTest() {
        History history = historyRepository.save(getHistory());

        historyRepository.deleteById(history.getId());

        Assertions.assertThrows(HistoryDoesNotExistException.class, () -> historyRepository.getHistoriesById(history.getId()));
    }

    @Test
    void deleteHistoryIfThrowsTest() {
        Assertions.assertThrows(HistoryDoesNotExistException.class,
                () ->historyService.deleteHistory(9999L));
    }

    @Test
    void updateHistory() {
        User user = userRepository.save( getUser());

        History history = historyService.saveHistory(getHistory(),user.getId());

        history.setStartTime(startTimer.minusMinutes(15));
        history.setEndTime(endTimer.minusMinutes(5));

        history =  historyService.updateHistory(history,history.getId());

        History historyFromBase = historyRepository.getHistoriesById(history.getId());
        history.setDuration(historyFromBase.getDuration());

        Assertions.assertEquals(history.toString(),historyFromBase.toString());

        historyRepository.deleteById(history.getId());
        userRepository.deleteById(user.getId());
    }

    @Test
    void updateHistoryPartially() {
        User user = userRepository.save( getUser());

        History history = historyService.saveHistory(getHistory(),user.getId());

        History historyToSend = History.builder().endTime(endTimer.minusMinutes(5)).build();

        history =  historyService.updateHistoryPartially(historyToSend,history.getId());

        History historyFromBase = historyRepository.getHistoriesById(history.getId());
        history.setDuration(historyFromBase.getDuration());

        Assertions.assertEquals(history.toString(),historyFromBase.toString());

        historyRepository.deleteById(history.getId());
        userRepository.deleteById(user.getId());
    }
}
