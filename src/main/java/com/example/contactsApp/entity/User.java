package com.example.contactsApp.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
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
    @Pattern(regexp = "^(.+)@(\\S+)$", message = "email must be valid")
    @Column(name = "email")
    private String email;
    @Pattern(regexp = "^[a-z0-9_-]{3,15}$",message = "username format is bad")
    @Column(name = "username")
    private String username;
//    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",message = "weak password")
    @Column(name = "password")
    private  String password;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) // TODO research cascadeType
    private List<Contact> allContacts;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) // TODO research cascadeType
    private List<PhoneNumber> phoneNumberList;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) // TODO research cascadeType
    private List<History> historyList;

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }
}
