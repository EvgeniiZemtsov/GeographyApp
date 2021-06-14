package com.eugeneze.springrepositories;

import com.eugeneze.models.Ocean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringOceanRepository extends JpaRepository<Ocean, Integer> {

    @Query("SELECT o FROM Ocean o WHERE o.name = ?1")
    Optional<Ocean> findOceanByName(String oceanName);
}
