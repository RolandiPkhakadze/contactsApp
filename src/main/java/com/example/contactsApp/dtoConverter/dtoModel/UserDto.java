package com.example.contactsApp.dtoConverter.dtoModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
    private Long id;
    private String email;
    private String username;
    private  String password;
}
