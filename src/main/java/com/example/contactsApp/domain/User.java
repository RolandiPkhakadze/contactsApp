package com.example.contactsApp.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;

@Builder
@Getter
@Setter
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
    //@Pattern(regexp = "^{8,}$",message = "weak password")
    @Column(name = "password")
    private  String password;
    @OneToMany(mappedBy = "user") // TODO research cascadeType
    private List<Contact> allContacts;
    @OneToMany(mappedBy = "user") // TODO research cascadeType
    private List<PhoneNumber> phoneNumberList;
    @OneToMany(mappedBy = "user") // TODO research cascadeType
    private List<History> historyList;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
