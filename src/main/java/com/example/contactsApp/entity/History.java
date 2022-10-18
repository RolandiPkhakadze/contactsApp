package com.example.contactsApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;

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
    @OneToOne
    @JoinColumn(name = "id")
    private PhoneNumber phoneNumber;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Transient
    private Long duration= Duration.between(startDate,endDate).toSeconds();

}
