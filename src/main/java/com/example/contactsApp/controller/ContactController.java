package com.example.contactsApp.controller;

import com.example.contactsApp.dto.ContactDto;
import com.example.contactsApp.dtoConverter.ContactConverter;
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
    private final ContactConverter contactConverter;

    @PostMapping(path = "/add-contact")
    public Contact addContact( @RequestParam Long userId,@Valid @RequestBody ContactDto contactDto){
        return contactService.addContact(contactConverter.dtoToEntity(contactDto),userId);
    }

    @PutMapping(path = "/update-contact/{id}")
    public  Contact updateContact( @Valid @RequestBody ContactDto contactDto,
                                    @PathVariable("id") Long id){
        Contact contact = contactConverter.dtoToEntity(contactDto);
        contact.setId(id);
        return contactService.updateContact(contact, id);
    }

    @PatchMapping(path = "/update-contact/{id}")
    public Contact updateContactPartially(@RequestBody ContactDto contactDto,
                                          @PathVariable("id") Long id) {
        return contactService.updateContactPartially(contactConverter.dtoToEntity(contactDto), id);
    }


    @DeleteMapping(path = "/delete-history")
    public String deletePhone(@RequestParam Long contactId){
        contactService.deleteContact(contactId);
        return "provider deleted";
    }
}
