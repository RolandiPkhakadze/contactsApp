package com.example.contactsApp.service.Impl;

import com.example.contactsApp.dtoConverter.converter.PhoneConverter;
import com.example.contactsApp.dtoConverter.dtoModel.PhoneDto;
import com.example.contactsApp.entity.Contact;
import com.example.contactsApp.entity.NumberProvider;
import com.example.contactsApp.entity.PhoneNumber;
import com.example.contactsApp.entity.User;
import com.example.contactsApp.repository.ContactRepository;
import com.example.contactsApp.repository.NumberProviderRepository;
import com.example.contactsApp.repository.PhoneNumberRepository;
import com.example.contactsApp.repository.UserRepository;
import com.example.contactsApp.service.ContactService;
import lombok.AllArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ContactServiceTests {
    @Autowired
    private ContactServiceImpl contactService;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NumberProviderRepository numberProviderRepository;
    @Autowired
    private PhoneNumberRepository phoneNumberRepository;
    @Test
    void    addContactTest() {
        User user = new User("tedhst@maqwil.com","sstwqname","TestPassword12");
        User userEntity = userRepository.save(user);
        NumberProvider provider = new NumberProvider();
        provider.setName("MAGTI");
        provider.setTariffForSame(1);
        provider.setTariffForGeo(1);
        provider.setTariffForNonGeo(5);
//        provider.getIsGeorgian(Boolean.TRUE);
        PhoneNumber phone = new PhoneNumber("571367644", 55L,numberProviderRepository.save(provider));
        phone = phoneNumberRepository.save(phone);
        Contact givenContact = new Contact(phone.getPhoneNumber(), phone,false,userEntity,"gigi","gd");
        //givenContact = contactRepository.save(givenContact);

        givenContact = contactService.addContact(givenContact, userEntity.getId());
        Contact contactFromDb = contactRepository.getContactById(phone.getPhoneNumber());

        System.out.println(givenContact + "\n"+contactFromDb);
        Assertions.assertThat(givenContact).usingRecursiveComparison().ignoringFields("id","phoneNumber","user" ).isEqualTo(contactFromDb);


        userRepository.deleteById(userEntity.getId());
        contactRepository.deleteById(contactFromDb.getId());
    }


    @Test
    void updateContactPartiallyTest() {
    }

    @Test
    void updateContactTest() {
    }

    @Test
    void deleteContactTest() {
    }
}
