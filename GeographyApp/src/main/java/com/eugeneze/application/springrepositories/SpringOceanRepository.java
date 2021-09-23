package com.eugeneze.application.springrepositories;

import com.eugeneze.models.Ocean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringOceanRepository extends JpaRepository<Ocean, Integer> {

    Optional<Ocean> findOceanByName(String oceanName);
}
