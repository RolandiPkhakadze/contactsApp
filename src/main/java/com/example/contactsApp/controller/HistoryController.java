package com.example.contactsApp.controller;

import com.example.contactsApp.entity.History;
import com.example.contactsApp.service.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "history")
public class HistoryController {

    private final HistoryService historyService;

    @GetMapping(path = "/histories")
    public List<History> getAllHistoriesForUser(@RequestParam Long userId) {
        return historyService.getAllHistoriesForUser(userId);
    }

    @PutMapping(path = "/add-history")
    public History addHistory(@Valid @RequestBody History history, @RequestParam Long userId) {
        return historyService.saveHistory(history,userId);
    }

    @DeleteMapping(path = "/delete-history")
    public String deleteHistory(@RequestParam Long historyId){
        historyService.deleteHistory(historyId);
        return "provider deleted";
    }
}
