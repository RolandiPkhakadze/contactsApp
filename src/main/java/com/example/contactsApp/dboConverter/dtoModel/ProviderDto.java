package com.example.contactsApp.dboConverter.dtoModel;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Data
public class ProviderDto {
    private Long id;
    private String  name;
    private Boolean isGeorgian;
    private Integer tariffForGeo;
    private Integer tariffForSame;
    private Integer tariffForNonGeo;
}
