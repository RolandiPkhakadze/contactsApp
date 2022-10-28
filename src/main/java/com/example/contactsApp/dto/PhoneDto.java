package com.example.contactsApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PhoneDto {
    @Pattern(regexp = "^[0-9]{9}$", message = "wrong phone number")
    private String phoneNumber;
    //a@Pattern(regexp = "\\d+", message = "You should enter only digits")
    private Long balance;
}
