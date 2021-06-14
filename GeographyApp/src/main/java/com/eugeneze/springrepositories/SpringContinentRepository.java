package com.eugeneze.springrepositories;

import com.eugeneze.models.Continent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringContinentRepository extends JpaRepository<Continent, Integer> {

    @Query("SELECT c FROM Continent c WHERE c.name = ?1")
    Optional<Continent> findContinentByName(String continentName);
}
