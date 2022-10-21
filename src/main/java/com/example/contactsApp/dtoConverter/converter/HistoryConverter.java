package com.example.contactsApp.dtoConverter.converter;

import com.example.contactsApp.dtoConverter.dtoModel.HistoryDto;
import com.example.contactsApp.entity.History;
import com.example.contactsApp.service.Impl.PhoneNumberServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class HistoryConverter {
    private final PhoneNumberServiceImpl phoneNumberService;

    public History dtoToEntity(HistoryDto historyDto) {
        History history = new History();
        history.setEndTime(historyDto.getEndTime());
        history.setStartTime(historyDto.getStartTime());
        history.setPhoneNumber(historyDto.getPhoneNumber());

        return history;
    }

    public List<History> dtoToEntity(List<HistoryDto> historyDto) {
        return historyDto.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }

    public HistoryDto entityToDto(History history) {

        return HistoryDto.builder()
                .endTime(history.getEndTime())
                .startTime(history.getStartTime())
                .phoneNumber(history.getPhoneNumber())
                .build();
    }

    public List<HistoryDto> entityToDto(List<History> history) {
        return history.stream().map(this::entityToDto).collect(Collectors.toList());
    }

}