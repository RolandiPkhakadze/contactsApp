package com.example.contactsApp.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class HistoryDto {

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String phoneNumber;
}
