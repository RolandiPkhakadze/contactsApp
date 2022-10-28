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
public class UserDto {
    private Long id;
    @Pattern(regexp = "^(.+)@(\\S+)$", message = "email must be valid")
    private String email;
    @Pattern(regexp = "^[a-z0-9_-]{3,15}$",message = "username format is bad")
    private String username;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",message = "weak password")
    private  String password;
}
