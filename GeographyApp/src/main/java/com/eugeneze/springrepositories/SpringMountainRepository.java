package com.eugeneze.springrepositories;

import com.eugeneze.models.Mountain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringMountainRepository extends JpaRepository<Mountain, Integer> {

    Optional<Mountain> findMountainByName(String mountainName);
}
