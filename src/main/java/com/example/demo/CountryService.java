package com.example.demo;

import com.example.demo.db.model.CountryEntity;
import com.example.demo.db.repository.CountryRepository;
import com.github.danik14.soap_demo.Country;
import com.github.danik14.soap_demo.Currency;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;

    @PostConstruct
    public void initData() {
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