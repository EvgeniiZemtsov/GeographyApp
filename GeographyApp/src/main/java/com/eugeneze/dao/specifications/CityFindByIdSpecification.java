package com.eugeneze.dao.specifications;

import com.eugeneze.models.City;

public class CityFindByIdSpecification implements Specification<City> {
    private int id;

    public CityFindByIdSpecification(int id) {
        this.id = id;
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
        return "FROM City where id = " + id;
    }
}
