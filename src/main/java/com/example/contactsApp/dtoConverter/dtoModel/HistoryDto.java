package com.example.contactsApp.dtoConverter.dtoModel;

import com.example.contactsApp.entity.PhoneNumber;
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
    private PhoneNumber phoneNumber;
}
