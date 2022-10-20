package com.example.contactsApp.controller;

import com.example.contactsApp.dtoConverter.converter.PhoneConverter;
import com.example.contactsApp.dtoConverter.dtoModel.PhoneDto;
import com.example.contactsApp.entity.PhoneNumber;
import com.example.contactsApp.service.Intf.PhoneNumberService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path  = "phone")
public class PhoneNumberController {
    private final PhoneNumberService phoneNumberService;
    private final PhoneConverter converter;

    @PutMapping(path = "/add-user-phone")
    public PhoneDto addUserPhone(@RequestParam Long userId, @RequestBody PhoneDto dto){

        PhoneNumber phone = phoneNumberService.addUserPhone(converter.dtoToEntity(dto),userId);
        return converter.entityToDto(phone);
    }

    @PostMapping(path = "/add-contact-phone")
    public PhoneDto addContactPhone( @RequestBody PhoneDto dto){
        PhoneNumber phone = phoneNumberService.addContactPhone(converter.dtoToEntity(dto));
        return converter.entityToDto(phone);
    }

    @PutMapping(path = "/update-phone/{id}")
    public PhoneDto updatePhone(  @RequestBody PhoneDto dto,
                                 @PathVariable("id") Long id){
        PhoneNumber phone = phoneNumberService.updatePhone(converter.dtoToEntity(dto),id);
        return converter.entityToDto(phone);
    }
    @PatchMapping(path = "/update-phone/{id}")
    public PhoneDto updatePhonePartially(  @RequestBody PhoneDto dto,
                                         @PathVariable("id") Long id){
        PhoneNumber phone = phoneNumberService.updatePhonePartially(converter.dtoToEntity(dto),id);
        return converter.entityToDto(phone);
    }

    @DeleteMapping(path = "/delete-phone")
    public String deletePhone(@RequestParam Long phoneId){
        phoneNumberService.deletePhone(phoneId);
        return "provider deleted";
    }
}
