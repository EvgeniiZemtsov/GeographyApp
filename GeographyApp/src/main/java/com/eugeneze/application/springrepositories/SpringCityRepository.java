package com.eugeneze.application.springrepositories;

import com.eugeneze.models.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringCityRepository extends JpaRepository<City, Integer> {

    Optional<City> findCityByName(String cityName);
}
