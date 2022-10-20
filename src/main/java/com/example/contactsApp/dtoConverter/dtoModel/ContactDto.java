package com.example.contactsApp.dtoConverter.dtoModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ContactDto {
    private String firstName;
    private String lastName;
    private Boolean isFavorite;
    private String phoneNumber;
}
