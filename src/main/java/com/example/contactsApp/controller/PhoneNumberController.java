package com.example.contactsApp.controller;

import com.example.contactsApp.entity.PhoneNumber;
import com.example.contactsApp.service.phoneServices.PhoneNumberService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(path  = "phone")
public class PhoneNumberController {
    private final PhoneNumberService phoneNumberService;

    @PutMapping(path = "/add-user-phone")
    public String addUserPhone(@RequestParam Long userId,@Valid @RequestBody PhoneNumber phone){
        phoneNumberService.addUserPhone(phone,userId);
        return "user phone added";
    }

    @PostMapping(path = "/add-contact-phone")
    public PhoneNumber addContactPhone(@Valid @RequestBody PhoneNumber phone){
        return phoneNumberService.addContactPhone(phone);
    }

    @PutMapping(path = "/update-phone/{id}")
    public PhoneNumber updatePhone(@Valid  @RequestBody PhoneNumber phone,
                                 @PathVariable("id") Long id){
        return phoneNumberService.updatePhone(phone,id);
    }
    @PatchMapping(path = "/update-phone/{id}")
    public PhoneNumber updatePhonePartially(@Valid  @RequestBody PhoneNumber phone,
                                     @PathVariable("id") Long id){
        return phoneNumberService.updatePhonePartially(phone,id);
    }

    @DeleteMapping(path = "/delete-phone")
    public String deletePhone(@RequestParam Long phoneId){
        phoneNumberService.deletePhone(phoneId);
        return "provider deleted";
    }
}
