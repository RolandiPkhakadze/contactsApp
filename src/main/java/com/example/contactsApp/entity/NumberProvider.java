package com.example.contactsApp.entity;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class NumberProvider {
    @Id
    @SequenceGenerator(
            name = "numberprovider_sequence",
            sequenceName = "numberprovider_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "numberprovider_sequence"
    )
    private Long id;

    private String  name;

    private boolean isGeorgian;

    private int tariffForGeo;

    private int tariffForSame;

    private int tariffForNonGeo;

    @OneToMany(mappedBy = "numberprovider")
    private List<PhoneNumber> phoneNumberList;

}
