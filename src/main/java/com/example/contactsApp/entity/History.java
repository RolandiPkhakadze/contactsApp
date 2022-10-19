package com.example.contactsApp.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "histories")
public class History {

    @Id
    @SequenceGenerator(
            name = "histories_id_seq",
            sequenceName = "histories_id_seq",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "histories_id_seq"
    )
    private Long Id;
    @ManyToOne
    @JoinColumn(name = "phone_id")
    private PhoneNumber phoneNumber;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Transient
    private Long duration = Duration.between(startDate, endDate).toSeconds();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
