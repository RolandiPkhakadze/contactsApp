package com.example.contactsApp.dtoConverter.dtoModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProviderDto {
    private Long id;
    private String  name;
    private Boolean isGeorgian;
    private Integer tariffForGeo;
    private Integer tariffForSame;
    private Integer tariffForNonGeo;
}
