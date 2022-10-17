package com.example.contactsApp.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contact_id_gen")
    @SequenceGenerator(name = "contact_id_gen", sequenceName = "contact_id_seq")
    private Long id;
    @OneToOne
    @JoinColumn(name = "phone_number_id")
    private PhoneNumber phoneNumber;
    @Column(name = "is_favorite")
    private boolean isFavorite;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
}
