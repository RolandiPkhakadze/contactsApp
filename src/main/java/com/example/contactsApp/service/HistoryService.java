package com.example.contactsApp.service;


import com.example.contactsApp.entity.History;
import com.example.contactsApp.repository.HistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {
    private HistoryRepository historyRepository;

    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public List<History> GetHistories(){
        return  historyRepository.findAll();
    }
}
