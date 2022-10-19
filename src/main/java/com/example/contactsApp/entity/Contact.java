package com.example.contactsApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.boot.convert.DataSizeUnit;

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
