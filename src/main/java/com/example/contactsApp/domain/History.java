package com.example.contactsApp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Builder // not a good option, only! for testing purposes u don't need to declare this annotation
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

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", duration=" + duration +
                '}';
    }
}
