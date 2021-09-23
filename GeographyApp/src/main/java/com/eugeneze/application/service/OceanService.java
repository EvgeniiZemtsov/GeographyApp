package com.eugeneze.application.service;

import com.eugeneze.models.Ocean;
import com.eugeneze.application.springrepositories.SpringOceanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OceanService {
    private final SpringOceanRepository repository;

    @Autowired
    public OceanService(SpringOceanRepository repository) {
        this.repository = repository;
    }

    public List<Ocean> getOceans() {
        return repository.findAll();
    }

    public Optional<Ocean> getOcean(int oceanId) {
        boolean exists = repository.existsById(oceanId);

        if (!exists) {
            throw new IllegalStateException("Ocean with id = " + oceanId + " doesn't exist");
        }

        return repository.findById(oceanId);
    }

    public Ocean addNewOcean(Ocean ocean) {
        Optional<Ocean> oceanOptional = repository.findOceanByName((String) ocean.getObjects()[1]);

        if (oceanOptional.isPresent()) {
            throw new IllegalStateException("Ocean already exists");
        }

        return repository.save(ocean);
    }

    public void deleteOcean(int oceanId) {
        boolean exists = repository.existsById(oceanId);

        if (!exists) {
            throw new IllegalStateException("Ocean with id = " + oceanId + " doesn't exist");
        }

        repository.deleteById(oceanId);
    }

    @Transactional
    public void updateOcean(int oceanId, String name, int maxDepth, int area) {
        Ocean ocean = repository.findById(oceanId).orElseThrow(() -> new IllegalStateException("Ocean with id = " + oceanId + " doesn't exist"));

        if (name != null && name.length() > 0 && !Objects.equals(ocean.getObjects()[1], name)) {
            ocean.setName(name);
        }

        if (maxDepth != 0 && (Integer)ocean.getObjects()[3] != maxDepth) {
            ocean.setMaxDepth(maxDepth);
        }

        if (area != 0 && (Integer)ocean.getObjects()[2] != area) {
            ocean.setArea(area);
        }
    }
}
