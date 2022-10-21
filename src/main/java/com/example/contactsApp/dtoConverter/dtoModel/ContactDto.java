package com.example.contactsApp.dtoConverter.dtoModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ContactDto {
    @NotBlank(message = "first name must be entered")
    private String firstName;
    private String lastName;
    private Boolean isFavorite;
    private String phoneNumber;
}
