package com.example.contactsApp.converter;

import com.example.contactsApp.dto.HistoryDto;
import com.example.contactsApp.domain.History;
import com.example.contactsApp.service.Impl.PhoneNumberServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class HistoryConverter {
    private final PhoneNumberServiceImpl phoneNumberService;

    public History toEntity(HistoryDto historyDto) {
        History history = new History();
        history.setEndTime(historyDto.getEndTime());
        history.setStartTime(historyDto.getStartTime());
        history.setPhoneNumber(phoneNumberService.getPhoneNumberByPhoneNumber(historyDto.getPhoneNumber()));

        return history;
    }

    public HistoryDto toDto(History history) {

        return HistoryDto.builder()
                .endTime(history.getEndTime())
                .startTime(history.getStartTime())
                .phoneNumber(history.getPhoneNumber().getPhoneNumber())
                .build();
    }

    public List<HistoryDto> entityToDto(List<History> history) {
        return history.stream().map(this::toDto).collect(Collectors.toList());
    }

}
