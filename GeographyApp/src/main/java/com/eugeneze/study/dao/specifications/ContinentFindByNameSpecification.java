package com.eugeneze.study.dao.specifications;

import com.eugeneze.models.Continent;

public class ContinentFindByNameSpecification implements Specification<Continent> {
    private String name;

    public ContinentFindByNameSpecification(String name) {
        this.name = name;
    }

    @Override
    public boolean isSatisfiedBy(Continent continent) {
        return true;
    }

    @Override
    public String toSqlClause() {
        return null;
    }

    @Override
    public String getHqlQuery() {
        return "FROM Continent where name = '" + name + "'";
    }
}
