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
@Table(name = "number_providers")
public class NumberProvider {
    @Id
    @SequenceGenerator(
            name = "number_providers_id_seq",
            sequenceName = "number_providers_id_seq",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "number_providers_id_seq"
    )
    private Long id;
    @Column(name = "name")
    private String  name;
    @Column(name = "is_georgian")
    private boolean isGeorgian;
    @Column(name = "tariff_for_geo")
    private int tariffForGeo;
    @Column(name = "tariff_for_same")
    private int tariffForSame;
    @Column(name = "tariff_for_non_geo")
    private int tariffForNonGeo;

    @OneToMany(mappedBy = "provider")
    private List<PhoneNumber> phoneNumberList;

}
