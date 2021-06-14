package com.eugeneze.springrepositories;

import com.eugeneze.models.Sea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringSeaRepository extends JpaRepository<Sea, Integer> {

    @Query("SELECT s FROM Sea s WHERE s.name = ?1")
    Optional<Sea> findSeaByName(String seaName);
}
