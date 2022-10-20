package com.example.contactsApp.dtoConverter.dtoModel;

import lombok.Data;

@Data
public class ProviderDto {
    private Long id;
    private String  name;
    private Boolean isGeorgian;
    private Integer tariffForGeo;
    private Integer tariffForSame;
    private Integer tariffForNonGeo;
}
