package com.eugeneze.application.service;

import com.eugeneze.models.Continent;
import com.eugeneze.application.springrepositories.SpringContinentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ContinentService {
    private final SpringContinentRepository repository;

    @Autowired
    public ContinentService(SpringContinentRepository repository) {
        this.repository = repository;
    }

    public List<Continent> getContinents() {
        return repository.findAll();
    }

    public Optional<Continent> getContinent(int continentId) {
        boolean exists = repository.existsById(continentId);

        if (!exists) {
            throw new IllegalStateException("Continent with id = " + continentId + " doesn't exist");
        }

        return repository.findById(continentId);
    }

    public Continent addNewContinent(Continent continent) {
        Optional<Continent> continentOptional = repository.findContinentByName((String) continent.getObjects()[1]);

        if (continentOptional.isPresent()) {
            throw new IllegalStateException("Continent already exists");
        }

        return repository.save(continent);
    }

    public void deleteContinent(int continentId) {
        boolean exists = repository.existsById(continentId);

        if (!exists) {
            throw new IllegalStateException("Continent with id = " + continentId + " doesn't exist");
        }

        repository.deleteById(continentId);
    }

    @Transactional
    public void updateContinent(int continentId, String name, Integer area) {
        Continent continent = repository.findById(continentId).orElseThrow(() -> new IllegalStateException("City with id = " + continentId + "doesn't exist"));

        if (name != null && name.length() > 0 && !Objects.equals(continent.getObjects()[1], name)) {
            continent.setName(name);
        }

        if (area != 0 && continent.getObjects()[2] != area) {
            continent.setArea(area);
        }
    }
}
