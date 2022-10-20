package com.example.contactsApp.dtoConverter.dtoModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PhoneDto {
    private Long id;
    private String phoneNumber;
    private Long balance;
}
