package com.example.contactsApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

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
    @Column(name = "phone_number")
    private String phoneNumber;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
    @Column(name = "balance")
    private Long balance;
    @ManyToOne
    @JoinColumn(name = "provider_id")
    @JsonIgnore
    private NumberProvider provider;
}
