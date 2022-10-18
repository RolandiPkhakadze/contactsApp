package com.example.contactsApp.controller;

import com.example.contactsApp.entity.Contact;
import com.example.contactsApp.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path  = "contact")
public class ContactController {
    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping(path = "add_contact")
    @PostMapping
    public String addPhone(@RequestParam Long userId, @RequestBody Contact contact){
        contactService.addContact(contact,userId);
        return "contact added";
    }
}
