package com.eugeneze.springrepositories;

import com.eugeneze.models.HeadOfState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringHeadOfStateRepository extends JpaRepository<HeadOfState, Integer> {

    Optional<HeadOfState> findHeadOfStateByFirstNameAndLastName(String firstName, String lastName);
}
