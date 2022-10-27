package com.example.contactsApp.controller;

import com.example.contactsApp.dtoConverter.converter.PhoneConverter;
import com.example.contactsApp.dtoConverter.dtoModel.PhoneDto;
import com.example.contactsApp.entity.PhoneNumber;
import com.example.contactsApp.service.PhoneNumberService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(path  = "phone")
public class PhoneNumberController {
    private final PhoneNumberService phoneNumberService;
    private final PhoneConverter converter;

    @PostMapping(path = "/add-user-phone")
    public PhoneDto addUserPhone( @RequestParam Long userId, @RequestBody  PhoneDto dto){
        @Valid PhoneNumber phone = phoneNumberService.addUserPhone(converter.dtoToEntity(dto),userId);
        return converter.entityToDto(phone);
    }

    @PostMapping(path = "/add-contact-phone")
    public PhoneDto addContactPhone(@Valid @RequestBody PhoneDto dto){
        PhoneNumber phone = phoneNumberService.addContactPhone(converter.dtoToEntity(dto));
        return converter.entityToDto(phone);
    }

    @PutMapping(path = "/update-phone")
    public PhoneDto updatePhone(@Valid  @RequestBody PhoneDto dto){
        PhoneNumber phone = phoneNumberService.updatePhone(converter.dtoToEntity(dto));
        return converter.entityToDto(phone);
    }

    @PatchMapping(path = "/update-phone")
    public PhoneDto updatePhonePartially(  @RequestBody PhoneDto dto){
        PhoneNumber phone = phoneNumberService.updatePhonePartially(converter.dtoToEntity(dto));
        return converter.entityToDto(phone);
    }

    @DeleteMapping(path = "/delete-phone")
    public String deletePhone(@RequestParam String phoneId){
        phoneNumberService.deletePhone(phoneId);
        return "provider deleted";
    }
}
