package com.example.contactsApp.dto;

import lombok.Data;

@Data
public class ContactDto {
    private String firstName;
    private String lastName;
    private Boolean isFavorite;
    private String phoneNumber;
}
