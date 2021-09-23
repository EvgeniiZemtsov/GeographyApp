package com.eugeneze.application.service;

import com.eugeneze.models.City;
import com.eugeneze.application.springrepositories.SpringCityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CityService {
    private final SpringCityRepository repository;

    @Autowired
    public CityService(SpringCityRepository repository) {
        this.repository = repository;
    }

    public List<City> getCities() {
        return repository.findAll();
    }

    public Optional<City> getCity(int cityId) {
        boolean exists = repository.existsById(cityId);

        if (!exists) {
            throw new IllegalStateException("City with id = " + cityId + " doesn't exist");
        }

        return repository.findById(cityId);
    }

    public City addNewCity(City city) {
        Optional<City> cityOptional = repository.findCityByName((String) city.getObjects()[1]);

        if (cityOptional.isPresent()) {
            throw new IllegalStateException("City already exists");
        }

        return repository.save(city);
    }

    public void deleteCity(int cityId) {
        boolean exists = repository.existsById(cityId);

        if (!exists) {
            throw new IllegalStateException("City with id = " + cityId + " doesn't exist");
        }

        repository.deleteById(cityId);
    }

    @Transactional
    public void updateCity(int cityId, String name, int population, int area) {
        City city = repository.findById(cityId).orElseThrow(() -> new IllegalStateException("City with id = " + cityId + "doesn't exist"));

        if (name != null && name.length() > 0 && !Objects.equals(city.getObjects()[1], name)) {
            city.setName(name);
        }

        if (population != 0 && (Integer)city.getObjects()[2] != population) {
            city.setPopulation(population);
        }

        if (area != 0 && (Integer)city.getObjects()[3] != area) {
            city.setArea(area);
        }
    }
}
