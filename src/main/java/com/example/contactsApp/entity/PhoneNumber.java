package com.example.contactsApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "phones")
public class PhoneNumber {
    @Id
    @SequenceGenerator(
            name = "phones_id_seq",
            sequenceName = "phones_id_seq",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "phones_id_seq"
    )
    private Long id;
    @Pattern(regexp = "^[0-9]{9}$",message = "wrong phone number")
    @Column(name = "phone_number")
    private String phoneNumber;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
    @Pattern(regexp = "^[1-9][0-9]$",message = "You should enter only digits")
    @Column(name = "balance")
    private Long balance;
    @ManyToOne
    @JoinColumn(name = "provider_id")
    @JsonIgnore
    private NumberProvider provider;
}
