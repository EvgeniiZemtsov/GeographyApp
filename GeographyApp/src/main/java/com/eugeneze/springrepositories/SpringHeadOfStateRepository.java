package com.eugeneze.springrepositories;

import com.eugeneze.models.HeadOfState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringHeadOfStateRepository extends JpaRepository<HeadOfState, Integer> {

    @Query("SELECT h FROM HeadOfState h WHERE h.firstName = ?1 AND h.lastName = ?2")
    Optional<HeadOfState> findHeadOfStateByFirstNameAndLastName(String firstName, String lastName);
}
