package com.example.contactsApp.controller;

import com.example.contactsApp.entity.Contact;
import com.example.contactsApp.entity.PhoneNumber;
import com.example.contactsApp.service.PhoneNumberService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path  = "phone")
public class PhoneNumberController {
    private final PhoneNumberService phoneNumberService;

    @PutMapping(path = "/add_user_phone")
    public String addUserPhone(@RequestParam Long userId, @RequestBody PhoneNumber phone){
        phoneNumberService.addUserPhone(phone,userId);
        return "user phone added";
    }

    @PutMapping(path = "/add_contact_phone")
    public String addContactPhone(@RequestParam Long contactId, @RequestBody PhoneNumber phone){
        phoneNumberService.addContactPhone(phone,contactId);
        return "contact phone added";
    }
}
