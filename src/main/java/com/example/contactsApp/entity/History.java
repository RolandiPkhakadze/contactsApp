package com.example.contactsApp.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "history_sequence")
    @SequenceGenerator(name = "history_sequence", sequenceName = "history_sequence")
    private Long Id;
    @ManyToOne
    @JoinColumn(name = "phone_number_id")
    private PhoneNumber phoneNumber;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    @Transient
    private Long duration;

}
