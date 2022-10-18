package com.example.contactsApp.controller;

import com.example.contactsApp.entity.PhoneNumber;
import com.example.contactsApp.service.PhoneNumberService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path  = "phone")
public class PhoneNumberController {
    private final PhoneNumberService phoneNumberService;

    @PutMapping(path = "/add-user-phone")
    public String addUserPhone(@RequestParam Long userId, @RequestBody PhoneNumber phone){
        phoneNumberService.addUserPhone(phone,userId);
        return "user phone added";
    }

    @PutMapping(path = "/add-contact-phone")
    public String addContactPhone(@RequestBody PhoneNumber phone){
        phoneNumberService.addContactPhone(phone);
        return "contact phone added";
    }

    @DeleteMapping(path = "/delete-phone")
    public String deletePhone(@RequestParam Long phoneId){
        phoneNumberService.deletePhone(phoneId);
        return "provider deleted";
    }
}
