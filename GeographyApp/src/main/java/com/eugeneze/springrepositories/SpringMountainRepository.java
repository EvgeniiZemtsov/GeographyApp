package com.eugeneze.springrepositories;

import com.eugeneze.models.Mountain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringMountainRepository extends JpaRepository<Mountain, Integer> {

    @Query("SELECT m FROM Mountain m WHERE m.name = ?1")
    Optional<Mountain> findMountainByName(String mountainName);
}
