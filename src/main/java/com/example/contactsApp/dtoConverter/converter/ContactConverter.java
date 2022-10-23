package com.example.contactsApp.dtoConverter.converter;


import com.example.contactsApp.dtoConverter.dtoModel.ContactDto;
import com.example.contactsApp.entity.Contact;
import com.example.contactsApp.repository.ContactRepository;
import com.example.contactsApp.repository.PhoneNumberRepository;
import com.example.contactsApp.service.Impl.PhoneNumberServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ContactConverter {

    private final PhoneNumberRepository phoneNumberRepository;
    public Contact dtoToEntity(ContactDto contactDto) {
        Contact contact = new Contact();
        contact.setFirstName(contactDto.getFirstName());
        contact.setLastName(contactDto.getLastName());
        contact.setIsFavorite(contactDto.getIsFavorite());
        contact.setId(contactDto.getPhoneNumber());
        return contact;
    }

    public List<Contact> dtoToEntity(List<ContactDto> contactDtos) {
        return contactDtos.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }

    public ContactDto entityToDto(Contact contact) {
        ContactDto contactDto = new ContactDto();
        contactDto.setFirstName(contact.getFirstName());
        contactDto.setLastName(contact.getLastName());
        contactDto.setIsFavorite(contact.getIsFavorite());
        contactDto.setPhoneNumber(contact.getId());
        return contactDto;
    }

    public List<ContactDto> entityToDto(List<Contact> contacts) {
        return contacts.stream().map(this::entityToDto).collect(Collectors.toList());
    }

}
