package com.eugeneze.springrepositories;

import com.eugeneze.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringCountryRepository extends JpaRepository<Country, Integer> {

    Optional<Country> findCountryByName(String countryName);
}
