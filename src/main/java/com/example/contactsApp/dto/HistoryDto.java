package com.example.contactsApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class HistoryDto {
    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String phoneNumber;
}
