package com.example.contactsApp.dtoConverter.dtoModel;

import lombok.Data;

@Data
public class ContactDto {
    private String firstName;
    private String lastName;
    private Boolean isFavorite;
    private String phoneNumber;
}
