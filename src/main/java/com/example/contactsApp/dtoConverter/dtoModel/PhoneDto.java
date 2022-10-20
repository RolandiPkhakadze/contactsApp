package com.example.contactsApp.dtoConverter.dtoModel;

import lombok.Data;

@Data
public class PhoneDto {
    private Long id;
    private String phoneNumber;
    private Long balance;
}
