package com.example.demo;

import com.example.demo.db.DatabaseConnectionChecker;
import com.example.demo.db.model.CountryEntity;
import com.example.demo.db.repository.CountryRepository;
import com.github.danik14.soap_demo.Country;
import com.github.danik14.soap_demo.Currency;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;
    private final DatabaseConnectionChecker connectionChecker;

    @PostConstruct
    public void initData() {
        if (!connectionChecker.isConnected()) {
            System.out.println("ERROR connecting to database");
            return;
        }
        System.out.println("DB connected successfully");
        countryRepository.save(CountryEntity.builder()
                .name("Kazakhstan")
                .population(18500000)
                .capital("Astana")
                .currency(Currency.KZT.value())
                .build()
        );
    }

    public Country findCountry(String name) {
        CountryEntity entity = countryRepository
                .findByName(name)
                .orElseThrow(EntityNotFoundException::new);
        Country country = new Country();
        country.setName(entity.getName());
        country.setPopulation(entity.getPopulation());
        country.setCapital(entity.getCapital());
        country.setCurrency(Currency.fromValue(entity.getCurrency()));
        return country;
    }
}