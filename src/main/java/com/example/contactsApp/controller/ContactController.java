package com.example.contactsApp.controller;

import com.example.contactsApp.entity.Contact;
import com.example.contactsApp.service.contactServices.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(path  = "contact")
public class ContactController {
    private final ContactService contactService;

    @PostMapping(path = "/add-contact")
    public Contact addContact( @RequestParam Long userId,@Valid @RequestBody Contact contact){
        return contactService.addContact(contact,userId);
    }

    @PutMapping(path = "/update-contact/{id}")
    public  Contact updateContact( @Valid @RequestBody Contact contact,
                                    @PathVariable("id") Long id){
        contact.setId(id);
        return contactService.updateContact(contact, id);
    }

    @PatchMapping(path = "/update-contact/{id}")
    public Contact updateContactPartially(@RequestBody Contact contact,
                                          @PathVariable("id") Long id) {
        return contactService.updateContactPartially(contact, id);
    }


    @DeleteMapping(path = "/delete-history")
    public String deletePhone(@RequestParam Long contactId){
        contactService.deleteContact(contactId);
        return "provider deleted";
    }
}
