package com.example.contactsApp.controller;

import com.example.contactsApp.entity.History;
import com.example.contactsApp.entity.PhoneNumber;
import com.example.contactsApp.service.*;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "history")
public class HistoryController {

    private final HistoryService historyService;
    private final PhoneNumberService phoneNumberService;
    private final UserService userService;

    public HistoryController(HistoryServiceImpl historyService, PhoneNumberServiceImpl phoneNumberService, UserService userService) {
        this.historyService = historyService;
        this.phoneNumberService = phoneNumberService;
        this.userService = userService;
    }


    @GetMapping(path = "/histories")
    public List<History> getAllHistoriesForUser(@RequestParam Long userId) {

        return historyService.getAllHistoriesForUser(userId);

    }

    @PostMapping(path = "/add-history")
    public History addHistory(History history) {
        return historyService.saveHistory(history);
    }



}
