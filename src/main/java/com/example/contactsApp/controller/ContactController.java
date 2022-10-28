package com.example.contactsApp.controller;

import com.example.contactsApp.converter.ContactConverter;
import com.example.contactsApp.dto.ContactDto;
import com.example.contactsApp.domain.Contact;
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
    public ContactDto addContact( @RequestParam Long userId,@Valid @RequestBody ContactDto contactDto){
        return contactConverter.entityToDto(
                contactService.addContact(contactConverter.dtoToEntity(contactDto),userId));
    }

    @PutMapping(path = "/update-contact")
    public  ContactDto updateContact( @Valid @RequestBody ContactDto contactDto){
        Contact contact = contactConverter.dtoToEntity(contactDto);
        return contactConverter.entityToDto(
                contactService.updateContact(contact));
    }

    @PatchMapping(path = "/update-contact")
    public ContactDto updateContactPartially(@RequestBody ContactDto contactDto) {
        return contactConverter.entityToDto(
                contactService.updateContactPartially(contactConverter.dtoToEntity(contactDto)));
    }


    @DeleteMapping(path = "/delete-contact")
    public String deleteContact(@RequestParam String contactId){
        contactService.deleteContact(contactId);
        return "provider deleted";
    }
}
