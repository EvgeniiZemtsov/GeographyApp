package com.eugeneze.service;

import com.eugeneze.models.City;
import com.eugeneze.models.HeadOfState;
import com.eugeneze.springrepositories.SpringHeadOfStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class HeadOfStateService {
    private final SpringHeadOfStateRepository repository;

    @Autowired
    public HeadOfStateService(SpringHeadOfStateRepository repository) {
        this.repository = repository;
    }

    public List<HeadOfState> getHeadsOfStates() {
        return repository.findAll();
    }

    public Optional<HeadOfState> getHeadOfState(int headOfStateId) {
        boolean exists = repository.existsById(headOfStateId);

        if (!exists) {
            throw new IllegalStateException("Head of state with id = " + headOfStateId + " doesn't exist");
        }

        return repository.findById(headOfStateId);
    }

    public HeadOfState addNewHeadOfState(HeadOfState headOfState) {
        Optional<HeadOfState> headOfStateOptional = repository.findHeadOfStateByFirstNameAndLastName((String) headOfState.getObjects()[1],
                (String) headOfState.getObjects()[2]);

        if (headOfStateOptional.isPresent()) {
            throw new IllegalStateException("Head of state already exists");
        }

        return repository.save(headOfState);
    }

    public void deleteHeadOfState(int headOfStateId) {
        boolean exists = repository.existsById(headOfStateId);

        if (!exists) {
            throw new IllegalStateException("Head of state with id = " + headOfStateId + " doesn't exist");
        }

        repository.deleteById(headOfStateId);
    }

    @Transactional
    public void updateHeadOfState(int headOfStateId, String firstName, String lastName, String title) {
        HeadOfState headOfState = repository.findById(headOfStateId).orElseThrow(() -> new IllegalStateException("Head of state with id = " + headOfStateId + "doesn't exist"));

        if (firstName != null && firstName.length() > 0 && !Objects.equals(headOfState.getObjects()[1], firstName)) {
            headOfState.setFirstName(firstName);
        }

        if (lastName != null && lastName.length() > 0 && !Objects.equals(headOfState.getObjects()[2], lastName)) {
            headOfState.setLastName(lastName);
        }

        if (title != null && title.length() > 0 && !Objects.equals(headOfState.getObjects()[4], title)) {
            headOfState.setTitle(title);
        }

    }
}
