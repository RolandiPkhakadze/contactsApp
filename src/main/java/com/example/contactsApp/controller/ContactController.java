package com.example.contactsApp.controller;

import com.example.contactsApp.entity.Contact;
import com.example.contactsApp.service.ContactService;
import com.example.contactsApp.service.ContactServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path  = "contact")
public class ContactController {
    private final ContactService contactService;

    @RequestMapping(path = "add_contact")
    @PutMapping
    public String addPhone(@RequestParam Long userId, @RequestBody Contact contact){
        contactService.addContact(contact,userId);
        System.out.println(userId);
        return "contact added";
    }
}
