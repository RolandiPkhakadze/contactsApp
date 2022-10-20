package com.example.contactsApp.dtoConverter.converter;

import com.example.contactsApp.dtoConverter.dtoModel.PhoneDto;
import com.example.contactsApp.entity.PhoneNumber;
import org.springframework.stereotype.Component;

@Component
public class PhoneConverter {
    public PhoneDto entityToDto(PhoneNumber phone){

        return PhoneDto.builder()
                .id(phone.getId())
                .phoneNumber(phone.getPhoneNumber())
                .balance(phone.getBalance())
                .build();
    }


    public PhoneNumber dtoToEntity(PhoneDto dto){
        PhoneNumber phone = new PhoneNumber();

        phone.setBalance(dto.getBalance());
        phone.setPhoneNumber(dto.getPhoneNumber());
        return phone;
    }

}
