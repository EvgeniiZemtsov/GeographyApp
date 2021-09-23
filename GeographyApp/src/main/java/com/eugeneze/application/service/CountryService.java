package com.eugeneze.application.service;

import com.eugeneze.models.Country;
import com.eugeneze.application.springrepositories.SpringCountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CountryService {
    private final SpringCountryRepository repository;

    @Autowired
    public CountryService(SpringCountryRepository repository) {
        this.repository = repository;
    }

    public List<Country> getCountries() {
       return repository.findAll();
    }

    public Optional<Country> getCountry(int countryId) {
        boolean exists = repository.existsById(countryId);

        if (!exists) {
            throw new IllegalStateException("Country with id = " + countryId + " doesn't exist");
        }

        return repository.findById(countryId);
    }

    public Country addNewCountry(Country country) {
        Optional<Country> countryOptional = repository.findCountryByName((String) country.getObjects()[1]);

        if (countryOptional.isPresent()) {
            throw new IllegalStateException("Country already exists");
        }

        return repository.save(country);
    }

    public void deleteCountry(int countryId) {
        boolean exists = repository.existsById(countryId);

        if (!exists) {
            throw new IllegalStateException("Country with id = " + countryId + " doesn't exist");
        }

        repository.deleteById(countryId);
    }

    @Transactional
    public void updateCountry(int countryId, String name, int population, int area) {
        Country country = repository.findById(countryId).orElseThrow(() -> new IllegalStateException("Country with id = " + countryId + " doesn't exist"));

        if (name != null && name.length() > 0 && !Objects.equals(country.getObjects()[1], name)) {
            country.setName(name);
        }

        if (population != 0 && (Integer)country.getObjects()[2] != population) {
            country.setPopulation(population);
        }

        if (area != 0 && (Integer)country.getObjects()[3] != area) {
            country.setArea(area);
        }
    }
}
