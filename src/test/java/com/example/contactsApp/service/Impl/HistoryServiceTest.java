package com.example.contactsApp.service.Impl;

import com.example.contactsApp.exceptions.CallTimesException;
import com.example.contactsApp.exceptions.HistoryDoesNotExistException;
import com.example.contactsApp.domain.History;
import com.example.contactsApp.domain.User;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@ActiveProfiles("test")
public class HistoryServiceTest {

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private HistoryService historyService;
    @Autowired
    private UserRepository userRepository;

    private static final LocalDateTime START_TIME = LocalDateTime.now().minusMinutes(2);
    private static final LocalDateTime END_TIME = LocalDateTime.now();
    private static final int NUM_HISTORIES_TO_ADD = 5;

    public static User getUser(){
        return User.builder()
                .email("test@user.user")
                .password("PassdsERQSaasdW")
                .username("qwert")
                .build();
    }


    public static History getHistory(){

        return History.builder()
                .endTime(END_TIME)
                .startTime(START_TIME)
                .duration(Duration.between(START_TIME, END_TIME).toSeconds())
                .build();
    }

    @Test
    void saveHistoryWithTimesExceptionTest() {
        User user = userRepository.save( getUser());

        History history =getHistory();
        history.setStartTime(END_TIME.plusHours(3));

        Assertions.assertThrows(CallTimesException.class,
                () -> historyService.save(history, user.getId()));

        userRepository.deleteById(user.getId());
    }

    @Test
    void saveHistoryTest() {
        User user = userRepository.save( getUser());

        History history = historyService.save(getHistory(),user.getId());
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
                () ->historyService.delete(9999L));
    }

    @Test
    void updateHistory() {
        User user = userRepository.save( getUser());

        History history = historyService.save(getHistory(),user.getId());

        history.setStartTime(START_TIME.minusMinutes(15));
        history.setEndTime(END_TIME.minusMinutes(5));

        history =  historyService.update(history,history.getId());

        History historyFromBase = historyRepository.getHistoriesById(history.getId());
        history.setDuration(historyFromBase.getDuration());

        Assertions.assertEquals(history.toString(),historyFromBase.toString());

        historyRepository.deleteById(history.getId());
        userRepository.deleteById(user.getId());
    }

    @Test
    void updateHistoryPartially() {
        User user = userRepository.save( getUser());

        History history = historyService.save(getHistory(),user.getId());

        History historyToSend = History.builder().endTime(END_TIME.minusMinutes(5)).build();

        history =  historyService.partiallyUpdate(historyToSend,history.getId());

        History historyFromBase = historyRepository.getHistoriesById(history.getId());
        history.setDuration(historyFromBase.getDuration());

        Assertions.assertEquals(history.toString(),historyFromBase.toString());

        historyRepository.deleteById(history.getId());
        userRepository.deleteById(user.getId());
    }

    @Test
    void endDateIsBeforeStartDateTest() {
        var user = userRepository.save( getUser());
        var history = historyService.save(getHistory(),user.getId());
        history.setStartTime(END_TIME);
        history.setEndTime(START_TIME);

        Assertions.assertThrows(CallTimesException.class, () -> historyService.update(history, history.getId()));

        historyRepository.deleteById(history.getId());
        userRepository.deleteById(user.getId());

    }

    @Test
    void getAllHistoriesTest() {

        var user = userRepository.save( getUser());
        var historyIds = addHistoriesAndGetIds(user);

        var historiesFromDbId =
                historyService.getAllHistoriesForUser(user.getId())
                        .stream()
                        .map(History::getId).toList();

        for(Long id : historiesFromDbId) {
            Assertions.assertTrue(historyIds.contains(id));
        }

        deleteAllHistories(historyIds);
        userRepository.deleteById(user.getId());

    }

    private void deleteAllHistories(List<Long> historyIds) {
        for(Long id : historyIds) {
            historyRepository.deleteById(id);
        }
    }

    private List<Long> addHistoriesAndGetIds(User user) {
        List<History> result = new ArrayList<>();
        for(int i = 0; i < NUM_HISTORIES_TO_ADD; i++) {
            result.add(historyService.save(getHistory(),user.getId()));
        }
        return result
                .stream()
                .map(History::getId)
                .collect(Collectors.toList());
    }
}
