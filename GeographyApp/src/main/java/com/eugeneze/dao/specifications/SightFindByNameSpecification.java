package com.eugeneze.dao.specifications;

import com.eugeneze.models.City;
import com.eugeneze.models.Sight;

public class SightFindByNameSpecification implements Specification<Sight> {
    private String name;

    public SightFindByNameSpecification(String name) {
        this.name = name;
    }

    @Override
    public boolean isSatisfiedBy(Sight sight) {
        return true;
    }

    @Override
    public String toSqlClause() {
        return null;
    }

    @Override
    public String getHqlQuery() {
        return "FROM Sight where name = '" + name + "'";
    }
}
