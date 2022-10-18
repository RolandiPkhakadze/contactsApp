package com.example.contactsApp.service;


import com.example.contactsApp.entity.History;
import com.example.contactsApp.repository.HistoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class HistoryServiceImpl implements HistoryService {
    private HistoryRepository historyRepository;

    @Override
    public List<History> GetHistories() {
        return  historyRepository.findAll();
    }
}
