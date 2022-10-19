package com.example.contactsApp.controller;

import com.example.contactsApp.entity.Contact;
import com.example.contactsApp.service.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path  = "contact")
public class ContactController {
    private final ContactService contactService;

    @PutMapping(path = "/add-contact")
    public String addContact(@RequestParam Long userId, @RequestBody Contact contact){
        contactService.addContact(contact,userId);
        return "contact added";
    }

    @DeleteMapping(path = "/delete-history")
    public String deletePhone(@RequestParam Long contactId){
        contactService.deleteContact(contactId);
        return "provider deleted";
    }
}
