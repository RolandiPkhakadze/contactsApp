package com.example.contactsApp.controller;

import com.example.contactsApp.dtoConverter.converter.HistoryConverter;
import com.example.contactsApp.dtoConverter.dtoModel.HistoryDto;
import com.example.contactsApp.entity.History;
import com.example.contactsApp.service.Intf.HistoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping(path = "history")
public class HistoryController {

    private final HistoryService historyService;
    private final HistoryConverter historyConverter;

    @GetMapping(path = "/histories")
    public List<History> getAllHistoriesForUser(@RequestParam Long userId) {
        return historyService.getAllHistoriesForUser(userId);

    }

    @PostMapping(path = "/add-history")
    public History addHistory(@Valid  @RequestBody HistoryDto historyDto, @RequestParam Long userId) {
        return historyService.saveHistory(historyConverter.dtoToEntity(historyDto),userId);
    }

    @PutMapping(path = "/update-history/{id}")
    public History updateHistory(@Valid  @RequestBody HistoryDto historyDto,
                                  @PathVariable("id") Long id){
        return historyService.updateHistory(historyConverter.dtoToEntity(historyDto),id);
    }

    @PatchMapping(path = "/update-history/{id}")
    public History updateHistoryPartially(  @RequestBody HistoryDto historyDto,
                                          @PathVariable("id") Long id){
        var history = historyConverter.dtoToEntity(historyDto);
        history.setId(id);
        return historyService.updateHistoryPartially(history,id);
    }

    @DeleteMapping(path = "/delete-history")
    public String deleteHistory(@RequestParam Long historyId){
        historyService.deleteHistory(historyId);
        return "provider deleted";
    }
}
