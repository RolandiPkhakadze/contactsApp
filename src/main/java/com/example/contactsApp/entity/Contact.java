package com.example.contactsApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "contacts")
public class Contact {
    @Id
    @SequenceGenerator(
            name = "contacts_id_seq",
            sequenceName = "contacts_id_seq",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "contacts_id_seq"
    )
    private Long id;
    @OneToOne
    @JoinColumn(name = "id")
    private PhoneNumber phoneNumber;
    @Column(name = "is_favorite")
    private boolean isFavorite;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
    @NotNull
    @NotBlank
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
}
