package com.example.contactsApp.service.phoneServices;

import com.example.contactsApp.entity.PhoneNumber;
import com.example.contactsApp.entity.User;
import com.example.contactsApp.repository.PhoneNumberRepository;
import com.example.contactsApp.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class PhoneNumberServiceImpl implements PhoneNumberService{
    private final UserRepository userRepository;
    private final PhoneNumberRepository phoneNumberRepository;

    @Override
    public PhoneNumber addUserPhone(PhoneNumber phone, Long userId) {
        User userOptional = userRepository.getUserById(userId);
        log.debug("dsfdsfsd");

        phone.setUser(userOptional);
        return phoneNumberRepository.save(phone);
    }

    @Override
    public PhoneNumber addContactPhone(PhoneNumber phone) {
        return phoneNumberRepository.save(phone);
    }

    @Override
    public void deletePhone(Long phoneId) {
        phoneNumberRepository.deleteById(phoneId);
    }

    @Override
    public PhoneNumber updatePhone(PhoneNumber phone, Long id) {
        phoneNumberRepository.getPhoneById(id);
        phone.setId(id);
        return phoneNumberRepository.save(phone);
    }

    @Override
    public PhoneNumber updatePhonePartially(PhoneNumber phone, Long id) {
        PhoneNumber phoneForSave = phoneNumberRepository.getPhoneById(id);
        String phoneNumber = phone.getPhoneNumber();
        phoneForSave.setPhoneNumber(phoneNumber !=null? phoneNumber : phoneForSave.getPhoneNumber());
        Long balance = phone.getBalance();
        phoneForSave.setBalance(balance !=null? balance : phoneForSave.getBalance());
        return phoneNumberRepository.save(phoneForSave);
    }

    @Override
    public PhoneNumber getPhoneNumberByPhoneNumber(String phoneNumber) {
        return phoneNumberRepository.getPhoneNumberByPhoneNumber(phoneNumber);
    }

}
