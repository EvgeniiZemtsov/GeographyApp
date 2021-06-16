package com.eugeneze.springrepositories;

import com.eugeneze.models.Sea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringSeaRepository extends JpaRepository<Sea, Integer> {

    Optional<Sea> findSeaByName(String seaName);
}
