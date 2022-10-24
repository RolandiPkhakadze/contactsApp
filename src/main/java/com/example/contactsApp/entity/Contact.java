package com.example.contactsApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "contacts")
public class Contact {
    @Id
    @Column(name = "id")
    private String id;
    @OneToOne
    @JoinColumn(name = "id")
    private PhoneNumber phoneNumber;
    @Column(name = "is_favorite")
    private Boolean isFavorite;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
    @NotNull
    @NotBlank(message = "first name must be entered")
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
}
