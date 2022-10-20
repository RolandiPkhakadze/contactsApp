package com.example.contactsApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;

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
    private Long id;
    @ManyToOne
    @JoinColumn(name = "phone_id")
    @JsonIgnore
    private PhoneNumber phoneNumber;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
    @Column(name = "start_date")
    private LocalDateTime startTime;
    @Column(name = "end_date")
    private LocalDateTime endTime;
    @Transient
    private Long duration;

    @PostLoad
    public void initDuring() {
        duration = Duration.between(startTime, endTime).toSeconds();
    }
}
