package com.example.contactsApp.dboConverter.dtoModel;

import lombok.Data;

@Data
public class PhoneDto {
    private Long id;
    private String phoneNumber;
    private Long balance;
}
