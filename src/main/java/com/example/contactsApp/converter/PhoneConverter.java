package com.example.contactsApp.converter;

import com.example.contactsApp.dto.PhoneDto;
import com.example.contactsApp.domain.PhoneNumber;
import org.springframework.stereotype.Component;

@Component
public class PhoneConverter {
    public PhoneDto entityToDto(PhoneNumber phone){

        return PhoneDto.builder()
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
