package com.example.contactsApp.service;

import com.example.contactsApp.domain.History;

import java.util.List;

public interface HistoryService {

    // you already in history service and  you can name save method instead of saveHistory
    History save(History history, Long userId);

    // need to implemet pagination because user can have a lot of histories
    List<History> getAllHistoriesForUser(Long userId);

    void delete(Long historyId);

    History update(History history, Long id);

    History partiallyUpdate(History history, Long id);
}
