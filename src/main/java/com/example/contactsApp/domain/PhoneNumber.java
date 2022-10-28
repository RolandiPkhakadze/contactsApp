package com.example.contactsApp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "phones")
public class PhoneNumber {
    @Id
    @Column(name = "phone_number")
    @Pattern(regexp = "^[0-9]{9}$",message = "wrong phone number")
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
    @OneToMany(mappedBy = "phoneNumber")
    @ToString.Exclude
    private List<History> historyList;

    @OneToOne(mappedBy = "phoneNumber")
    private Contact contact;

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", balance=" + balance +
                '}';
    }
}
