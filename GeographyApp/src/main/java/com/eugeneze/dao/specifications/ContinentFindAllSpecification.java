package com.eugeneze.dao.specifications;

import com.eugeneze.models.Continent;

public class ContinentFindAllSpecification implements Specification<Continent> {
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
        return "FROM Continent";
    }
}
