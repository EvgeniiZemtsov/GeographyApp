package com.eugeneze.dao.specifications;

import com.eugeneze.models.City;

public class CityFindByNameSpecification implements Specification<City> {
    private String name;

    public CityFindByNameSpecification(String name) {
        this.name = name;
    }

    @Override
    public boolean isSatisfiedBy(City city) {
        return true;
    }

    @Override
    public String toSqlClause() {
        return null;
    }

    @Override
    public String getHqlQuery() {
        return "FROM City where name = '" + name + "'";
    }
}
