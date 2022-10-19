package com.example.contactsApp.controller;

import com.example.contactsApp.entity.NumberProvider;
import com.example.contactsApp.service.NumberProviderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "provider")
public class NumberProviderController {

    private final NumberProviderService numberProviderService;

    @PutMapping(path = "/add-provider")
    public String addProvider(@RequestBody NumberProvider provider){
        numberProviderService.addProvider(provider);
        return "provider added";
    }

    @DeleteMapping(path = "/delete-provider")
    public String deleteProvider(@RequestParam Long providerId){
        numberProviderService.deleteProvider(providerId);
        return "provider deleted";
    }
}
