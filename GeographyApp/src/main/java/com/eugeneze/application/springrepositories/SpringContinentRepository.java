package com.eugeneze.application.springrepositories;

import com.eugeneze.models.Continent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringContinentRepository extends JpaRepository<Continent, Integer> {

    Optional<Continent> findContinentByName(String continentName);
}
