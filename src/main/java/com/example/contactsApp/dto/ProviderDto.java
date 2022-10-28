package com.example.contactsApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProviderDto {
    private Long id;
    @Pattern(regexp = "[A-Z]+$", message = "provider name should consist only with uppercase letters")
    private String  name;
    private Boolean isGeorgian;
    @Min(value = 0,message = "minimum tariff is 0")
    private Integer tariffForGeo;
    @Min(value = 0,message = "minimum tariff is 0")
    private Integer tariffForSame;
    @Min(value = 0,message = "minimum tariff is 0")
    private Integer tariffForNonGeo;
}
