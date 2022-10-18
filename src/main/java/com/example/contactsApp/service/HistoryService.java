package com.example.contactsApp.service;

import com.example.contactsApp.entity.History;
import com.example.contactsApp.repository.HistoryRepository;

import java.util.List;

public interface HistoryService {

    List<History> GetHistories();
}
