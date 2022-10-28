package com.example.contactsApp.controller;

import com.example.contactsApp.converter.HistoryConverter;
import com.example.contactsApp.dto.HistoryDto;
import com.example.contactsApp.domain.History;
import com.example.contactsApp.service.HistoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@AllArgsConstructor
public class HistoryController {

    private final HistoryService historyService;
    private final HistoryConverter historyConverter;

    @GetMapping("/users/{userId}/histories")
    public List<HistoryDto> getAllHistoriesForUser(@PathVariable Long userId) {
        return historyConverter.entityToDto(historyService.getAllHistoriesForUser(userId));
    }

    @PostMapping("/users/{userId}/histories")
    public History addHistory(@Valid @RequestBody HistoryDto historyDto, @PathVariable Long userId) {
        return historyService.save(historyConverter.toEntity(historyDto),userId);
    }

    @PutMapping(path = "/histories/{id}")
    public History updateHistory(@Valid  @RequestBody HistoryDto historyDto,
                                  @PathVariable("id") Long id){
        return historyService.update(historyConverter.toEntity(historyDto),id);
    }

    @PatchMapping(path = "/histories/{id}")
    public History updateHistoryPartially( @RequestBody HistoryDto historyDto,
                                          @PathVariable("id") Long id){
        var history = historyConverter.toEntity(historyDto);
        history.setId(id);
        return historyService.partiallyUpdate(history,id);
    }

    @DeleteMapping(path = "/histories/{historyId}")
    public String deleteHistory(@PathVariable Long historyId){
        historyService.delete(historyId);
        return "provider deleted";
    }
}
