package com.example.contactsApp.controller;

import com.example.contactsApp.dtoConverter.converter.ContactConverter;
import com.example.contactsApp.dtoConverter.dtoModel.ContactDto;
import com.example.contactsApp.entity.Contact;
import com.example.contactsApp.service.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(path  = "contact")
public class ContactController {
    private final ContactService contactService;
    private final ContactConverter contactConverter;

    @PostMapping(path = "/add-contact")
    public Contact addContact( @RequestParam Long userId,@Valid @RequestBody ContactDto contactDto){
        return contactService.addContact(contactConverter.dtoToEntity(contactDto),userId);
    }

    @PutMapping(path = "/update-contact")
    public  Contact updateContact( @Valid @RequestBody ContactDto contactDto){
        Contact contact = contactConverter.dtoToEntity(contactDto);
        return contactService.updateContact(contact);
    }

    @PatchMapping(path = "/update-contact")
    public Contact updateContactPartially(@RequestBody ContactDto contactDto) {
        return contactService.updateContactPartially(contactConverter.dtoToEntity(contactDto));
    }


    @DeleteMapping(path = "/delete-history")
    public String deletePhone(@RequestParam Long contactId){
        contactService.deleteContact(contactId);
        return "provider deleted";
    }
}
