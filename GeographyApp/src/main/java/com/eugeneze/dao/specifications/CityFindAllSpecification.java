package com.eugeneze.dao.specifications;

import com.eugeneze.models.City;

public class CityFindAllSpecification implements Specification<City> {
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
        return "FROM City";
    }
}
