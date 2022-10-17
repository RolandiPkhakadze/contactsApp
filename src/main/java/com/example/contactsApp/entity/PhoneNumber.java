package com.example.contactsApp.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class PhoneNumber {
    @Id
    @SequenceGenerator(
            name = "NumberProvider_sequence",
            sequenceName = "NumberProvider_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "NumberProvider_sequence"
    )
    private Long id;
    private String phonenumber;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    private Long balance;
    @ManyToOne
    @JoinColumn(name = "providerId")
    private NumberProvider provider;
}
