package com.eugeneze.springrepositories;

import com.eugeneze.models.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringCityRepository extends JpaRepository<City, Integer> {

    @Query("SELECT c FROM City c WHERE c.name = ?1")
    Optional<City> findCityByName(String cityName);
}
