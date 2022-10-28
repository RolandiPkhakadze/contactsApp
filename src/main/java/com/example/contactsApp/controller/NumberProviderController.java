package com.example.contactsApp.controller;

import com.example.contactsApp.dto.ProviderDto;
import com.example.contactsApp.service.NumberProviderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(path = "providers")
public class NumberProviderController {


//  usually you need to inject only service here
//  if we need to process complex validation before starting to do business logic
//  in these cases you need add some validators components or services
    private final NumberProviderService numberProviderService;

    @PostMapping
    public ProviderDto addProvider(@RequestBody @Valid ProviderDto dto) {
        return numberProviderService.addProvider(dto);
    }

    @PutMapping(path = "/{id}")
    public ProviderDto updateProvider(@RequestBody ProviderDto dto,
                                      @PathVariable("id") Long id) {
        return numberProviderService.updateProvider(dto, id);
    }

    @PatchMapping(path = "/{id}")
    public ProviderDto updateProviderPartially(@RequestBody @Valid ProviderDto dto,
                                               @PathVariable("id") Long id) {
        return numberProviderService.addProviderPartially(dto, id);
    }

    @DeleteMapping
    public void deleteProvider(@RequestParam Long providerId) {
        numberProviderService.deleteProvider(providerId);
    }
}
