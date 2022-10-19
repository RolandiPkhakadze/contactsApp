package com.example.contactsApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @SequenceGenerator(
            name = "users_seq",
            sequenceName = "users_seq",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "users_seq"
    )
    private Long id;

    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    @Email
    @Column(name = "email")
    private String email;
    @Pattern(regexp = "^[a-z0-9_-]{3,15}$")
    @Column(name = "username")
    private String username;
    @Pattern(regexp = "\"^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$\"")
    @Column(name = "password")
    private  String password;
    @OneToMany(mappedBy = "user")
    private List<Contact> allContacts;
    @OneToMany(mappedBy = "user")
    private List<PhoneNumber> phoneNumberList;
    @OneToMany(mappedBy = "user")
    private List<History> historyList;
}
