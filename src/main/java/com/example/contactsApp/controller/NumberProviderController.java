package com.example.contactsApp.controller;

import com.example.contactsApp.entity.NumberProvider;
import com.example.contactsApp.service.providerServices.NumberProviderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(path = "provider")
public class NumberProviderController {

    private final NumberProviderService numberProviderService;

    @PostMapping(path = "/add-provider")
    public NumberProvider addProvider(@Valid  @RequestBody NumberProvider provider){

        return numberProviderService.addProvider(provider);
    }

    @PutMapping(path = "/update-provider/{id}")
    public NumberProvider updateHistory(@Valid  @RequestBody NumberProvider provider,
                                     @PathVariable("id") Long id){
        provider.setId(id);
        return numberProviderService.addProvider(provider);
    }

    @DeleteMapping(path = "/delete-provider")
    public String deleteProvider(@RequestParam Long providerId){
        numberProviderService.deleteProvider(providerId);
        return "provider deleted";
    }
}
