package com.example.contactsApp.Exception;

import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
public class CallTimesException extends RuntimeException {

    public CallTimesException(LocalDateTime startTime,LocalDateTime endTime)
    {
        super(String.format("call start time: %s must be less than end time: %s",startTime.toString(),endTime.toString()));
    }
}
