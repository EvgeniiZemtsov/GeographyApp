package com.eugeneze.service;

import com.eugeneze.models.Sight;
import com.eugeneze.springrepositories.SpringSightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SightService {
    private final SpringSightRepository repository;

    @Autowired
    public SightService(SpringSightRepository repository) {
        this.repository = repository;
    }

    public List<Sight> getSights() {
        return repository.findAll();
    }

    public Optional<Sight> getSight(int sightId) {
        boolean exists = repository.existsById(sightId);

        if (!exists) {
            throw new IllegalStateException("Sight with id = " + sightId + " doesn't exist");
        }

        return repository.findById(sightId);
    }

    public Sight addNewSight(Sight sight) {
        Optional<Sight> sightOptional = repository.findSightByName((String) sight.getObjects()[1]);

        if (sightOptional.isPresent()) {
            throw new IllegalStateException("Sight already exists");
        }

        return repository.save(sight);
    }

    public void deleteSight(int sightId) {
        boolean exists = repository.existsById(sightId);

        if (!exists) {
            throw new IllegalStateException("Sight with id = " + sightId + " doesn't exist");
        }

        repository.deleteById(sightId);
    }

    @Transactional
    public void updateSight(int sightId, String name) {
        Sight sight = repository.findById(sightId).orElseThrow(() -> new IllegalStateException("Sight with id = " + sightId + " doesn't exist"));

        if (name != null && name.length() > 0 && !Objects.equals(sight.getObjects()[1], name)) {
            sight.setName(name);
        }

    }
}
