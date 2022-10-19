package com.example.contactsApp.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
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
    @Pattern(regexp = "^[A-Z]$", message = "provider name should consist only with uppercase letters")
    @Column(name = "name")
    private String  name;
    @Column(name = "is_georgian")
    private boolean isGeorgian;
    @Min(value = 0,message = "minimum tariff is 0")
    @Column(name = "tariff_for_geo")
    private int tariffForGeo;
    @Min(value = 0,message = "minimum tariff is 0")
    @Column(name = "tariff_for_same")
    private int tariffForSame;
    @Min(value = 0,message = "minimum tariff is 0")
    @Column(name = "tariff_for_non_geo")
    private int tariffForNonGeo;
    @OneToMany(mappedBy = "provider")
    private List<PhoneNumber> phoneNumberList;

}
