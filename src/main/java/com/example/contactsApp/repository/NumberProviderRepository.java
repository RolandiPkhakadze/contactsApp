package com.example.contactsApp.repository;

import com.example.contactsApp.Exception.ProviderDoesNotExistException;
import com.example.contactsApp.entity.NumberProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NumberProviderRepository  extends JpaRepository<NumberProvider, Long> {
    default NumberProvider getNumberProviderById(Long id) {
        return findNumberProviderById(id).orElseThrow(() -> new ProviderDoesNotExistException(id));
    }

    Optional<NumberProvider> findNumberProviderById(Long id);
}
