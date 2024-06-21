package com.example.demo.db.repository;

import com.example.demo.db.model.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, UUID> {
    public Optional<CountryEntity> findByName(String name);
}