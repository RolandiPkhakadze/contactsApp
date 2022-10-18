package com.example.contactsApp.service;

import com.example.contactsApp.entity.History;

import java.util.List;

public interface HistoryService {

    History saveHistory(History history, Long userId);

    List<History> getAllHistoriesForUser(Long userId);

    void deleteHistory(Long historyId);
}
