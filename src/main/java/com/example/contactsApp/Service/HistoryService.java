package com.example.contactsApp.Service;


import com.example.contactsApp.repository.HistoryRepository;
import org.springframework.stereotype.Service;

@Service
public class HistoryService {
    private HistoryRepository historyRepository;

    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }
}
