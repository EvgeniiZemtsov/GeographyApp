package com.eugeneze.service;

import com.eugeneze.models.Ocean;
import com.eugeneze.models.Sea;
import com.eugeneze.springrepositories.SpringSeaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SeaService {
    private final SpringSeaRepository repository;

    @Autowired
    public SeaService(SpringSeaRepository repository) {
        this.repository = repository;
    }

    public List<Sea> getSeas() {
        return repository.findAll();
    }

    public Optional<Sea> getSea(int seaId) {
        boolean exists = repository.existsById(seaId);

        if (!exists) {
            throw new IllegalStateException("Sea with id = " + seaId + " doesn't exist");
        }

        return repository.findById(seaId);
    }

    public Sea addNewSea(Sea sea) {
        Optional<Sea> seaOptional = repository.findSeaByName((String) sea.getObjects()[1]);

        if (seaOptional.isPresent()) {
            throw new IllegalStateException("Sea already exists");
        }

        return repository.save(sea);
    }

    public void deleteSea(int seaId) {
        boolean exists = repository.existsById(seaId);

        if (!exists) {
            throw new IllegalStateException("Sea with id = " + seaId + " doesn't exist");
        }

        repository.deleteById(seaId);
    }

    @Transactional
    public void updateSea(int seaId, String name, int maxDepth, int area) {
        Sea sea = repository.findById(seaId).orElseThrow(() -> new IllegalStateException("Sea with id = " + seaId + "doesn't exist"));

        if (name != null && name.length() > 0 && !Objects.equals(sea.getObjects()[1], name)) {
            sea.setName(name);
        }

        if (maxDepth != 0 && (Integer)sea.getObjects()[3] != maxDepth) {
            sea.setMaxDepth(maxDepth);
        }

        if (area != 0 && (Integer)sea.getObjects()[2] != area) {
            sea.setArea(area);
        }
    }
}
