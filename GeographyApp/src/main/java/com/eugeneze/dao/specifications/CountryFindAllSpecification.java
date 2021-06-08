package com.eugeneze.dao.specifications;

import com.eugeneze.models.Country;

public class CountryFindAllSpecification implements Specification<Country> {
    @Override
    public boolean isSatisfiedBy(Country country) {
        return true;
    }

    @Override
    public String toSqlClause() {
        return null;
    }

    @Override
    public String getHqlQuery() {
        return "FROM Country";
    }
}
