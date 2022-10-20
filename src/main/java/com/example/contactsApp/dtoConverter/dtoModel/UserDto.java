package com.example.contactsApp.dtoConverter.dtoModel;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String username;
    private  String password;
}
