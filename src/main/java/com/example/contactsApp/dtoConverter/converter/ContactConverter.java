package com.example.contactsApp.dtoConverter.converter;


import com.example.contactsApp.dtoConverter.dtoModel.ContactDto;
import com.example.contactsApp.entity.Contact;
import org.springframework.stereotype.Component;

@Component
public class ContactConverter {


    public Contact dtoToEntity(ContactDto contactDto) {
        Contact contact = new Contact();
        contact.setFirstName(contactDto.getFirstName());
        contact.setLastName(contactDto.getLastName());
        contact.setIsFavorite(contactDto.getIsFavorite());
        contact.setId(contactDto.getPhoneNumber());
      //  contact.setId(phoneNumberService.getPhoneNumberByPhoneNumber(contactDto.getPhoneNumber()).getId());
        return contact;
    }


    public ContactDto entityToDto(Contact contact) {
        ContactDto contactDto = new ContactDto();
        contactDto.setFirstName(contact.getFirstName());
        contactDto.setLastName(contact.getLastName());
        contactDto.setIsFavorite(contact.getIsFavorite());
        contactDto.setPhoneNumber(contact.getPhoneNumber().getPhoneNumber());
        return contactDto;
    }


}
