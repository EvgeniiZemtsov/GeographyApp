package com.eugeneze.dao;

import com.eugeneze.models.Country;

import java.util.List;

public interface Repository {

    List<Country> query(Specification<Country> countrySpecification);

    void create(Country country);
    void update(Country country);
    void delete(String id);
}
