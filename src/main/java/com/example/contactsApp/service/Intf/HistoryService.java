package com.example.contactsApp.service.Intf;

import com.example.contactsApp.entity.History;

import java.util.List;

public interface HistoryService {

    History saveHistory(History history, Long userId);

    List<History> getAllHistoriesForUser(Long userId);

    void deleteHistory(Long historyId);

    History updateHistory(History history, Long id);

    History updateHistoryPartially(History history, Long id);
}
