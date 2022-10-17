package com.example.contactsApp.repository;

import com.example.contactsApp.entity.NumberProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NumberProviderRepository  extends JpaRepository<NumberProvider, Long> {
}
