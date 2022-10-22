package com.example.contactsApp.controller;

import com.example.contactsApp.dtoConverter.converter.ProviderConverter;
import com.example.contactsApp.dtoConverter.dtoModel.ProviderDto;
import com.example.contactsApp.entity.NumberProvider;
import com.example.contactsApp.service.NumberProviderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(path = "provider")
public class NumberProviderController {

    private final NumberProviderService numberProviderService;
    private final ProviderConverter converter;

    @PostMapping(path = "/add-provider")
    public ProviderDto addProvider(@RequestBody @Valid ProviderDto dto){
        @Valid NumberProvider provider = converter.dtoToEntity(dto);
        return converter.entityToDto(numberProviderService.addProvider(provider));
    }

    @PutMapping(path = "/update-provider/{id}")
    public ProviderDto updateProvider(@RequestBody ProviderDto dto,
                                     @PathVariable("id") Long id){
        @Valid NumberProvider provider = converter.dtoToEntity(dto);
        return converter.entityToDto(numberProviderService.updateProvider(provider,id));
    }

    @PatchMapping(path = "/update-provider/{id}")
    public ProviderDto updateProviderPartially(@RequestBody @Valid ProviderDto dto,
                                        @PathVariable("id") Long id){
        @Valid NumberProvider provider = converter.dtoToEntity(dto);
        return converter.entityToDto(numberProviderService.addProviderPartially(provider,id));
    }

    @DeleteMapping(path = "/delete-provider")
    public String deleteProvider(@RequestParam Long providerId){
        numberProviderService.deleteProvider(providerId);
        return "provider deleted";
    }
}
