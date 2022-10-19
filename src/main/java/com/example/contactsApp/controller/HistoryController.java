package com.example.contactsApp.controller;

import com.example.contactsApp.entity.History;
import com.example.contactsApp.service.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.Duration.between;

@RestController
@AllArgsConstructor
@RequestMapping(path = "history")
public class HistoryController {

    private final HistoryService historyService;

    @GetMapping(path = "/histories")
    public List<History> getAllHistoriesForUser(@RequestParam Long userId) {
        return historyService.getAllHistoriesForUser(userId)
                .stream()
                .peek(history -> history.setDuration((
                        between(history.getStartDate(), history.getEndDate()).toSeconds())))
                .collect(Collectors.toList());

    }

    @PutMapping(path = "/add-history")
    public void addHistory(@Valid  @RequestBody History history, @RequestParam Long userId) {
        historyService.saveHistory(history,userId);
    }

    @DeleteMapping(path = "/delete-history")
    public String deleteHistory(@RequestParam Long historyId){
        historyService.deleteHistory(historyId);
        return "provider deleted";
    }
}