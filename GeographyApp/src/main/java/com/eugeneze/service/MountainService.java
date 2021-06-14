package com.eugeneze.service;

import com.eugeneze.models.Language;
import com.eugeneze.models.Mountain;
import com.eugeneze.springrepositories.SpringMountainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MountainService {
    private final SpringMountainRepository repository;

    @Autowired
    public MountainService(SpringMountainRepository repository) {
        this.repository = repository;
    }

    public List<Mountain> getMountains() {
        return repository.findAll();
    }

    public Optional<Mountain> getMountain(int mountainId) {
        boolean exists = repository.existsById(mountainId);

        if (!exists) {
            throw new IllegalStateException("Mountain with id = " + mountainId + " doesn't exist");
        }

        return repository.findById(mountainId);
    }

    public Mountain addNewMountain(Mountain mountain) {
        Optional<Mountain> mountainOptional = repository.findMountainByName((String) mountain.getObjects()[1]);

        if (mountainOptional.isPresent()) {
            throw new IllegalStateException("Mountain already exists");
        }

        return repository.save(mountain);
    }

    public void deleteMountain(int mountainId) {
        boolean exists = repository.existsById(mountainId);

        if (!exists) {
            throw new IllegalStateException("Mountain with id = " + mountainId + " doesn't exist");
        }

        repository.deleteById(mountainId);
    }

    @Transactional
    public void updateMountain(int mountainId, String name, int height) {
        Mountain mountain = repository.findById(mountainId).orElseThrow(() -> new IllegalStateException("Mountain with id = " + mountainId + "doesn't exist"));

        if (name != null && name.length() > 0 && !Objects.equals(mountain.getObjects()[1], name)) {
            mountain.setName(name);
        }

        if (height != 0 && (Integer)mountain.getObjects()[2] != height) {
            mountain.addHeight(height);
        }
    }
}
