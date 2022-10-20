package com.example.contactsApp.dboConverter.dtoModel;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String username;
    private  String password;
}
