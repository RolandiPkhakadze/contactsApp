package com.example.contactsApp.dtoConverter.converter;

import com.example.contactsApp.dtoConverter.dtoModel.PhoneDto;
import com.example.contactsApp.entity.PhoneNumber;
import org.springframework.stereotype.Component;

@Component
public class PhoneConverter {
    public PhoneDto entityToDto(PhoneNumber phone){
        PhoneDto dto = new PhoneDto();

        dto.setId(phone.getId());
        dto.setBalance(phone.getBalance());
        dto.setPhoneNumber(phone.getPhoneNumber());
        return dto;
    }


    public PhoneNumber dtoToEntity(PhoneDto dto){
        PhoneNumber phone = new PhoneNumber();

        phone.setBalance(dto.getBalance());
        phone.setPhoneNumber(dto.getPhoneNumber());
        return phone;
    }

}
