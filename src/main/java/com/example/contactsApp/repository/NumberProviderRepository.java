package com.example.contactsApp.repository;

import com.example.contactsApp.exceptions.ProviderDoesNotExistException;
import com.example.contactsApp.domain.NumberProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NumberProviderRepository  extends JpaRepository<NumberProvider, Long> {
    default NumberProvider getNumberProviderById(Long id) {
        return findNumberProviderById(id).orElseThrow(() -> new ProviderDoesNotExistException(String.format("provider with id: %d was not found.",id)));
    }

    Optional<NumberProvider> findNumberProviderById(Long id);
}
