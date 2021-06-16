package com.eugeneze.springrepositories;

import com.eugeneze.models.Sight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringSightRepository extends JpaRepository<Sight, Integer> {

    Optional<Sight> findSightByName(String sightName);
}
