package com.example.contactsApp.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "phone")
public class PhoneNumber {
    @Id
    @SequenceGenerator(
            name = "phone_sequence",
            sequenceName = "phone_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "phone_sequence"
    )
    private Long id;
    @Column(name = "phone_number")
    private String phoneNumber;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "balance")
    private Long balance;
    @ManyToOne
    @JoinColumn(name = "provider_id")
    private NumberProvider provider;
}
