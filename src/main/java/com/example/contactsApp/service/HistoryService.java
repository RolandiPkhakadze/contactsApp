package com.example.contactsApp.service;

import com.example.contactsApp.entity.History;
import com.example.contactsApp.entity.PhoneNumber;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface HistoryService {

    History saveHistory(History history);

    List<History> getAllHistoriesForUser(Long userId);
}
