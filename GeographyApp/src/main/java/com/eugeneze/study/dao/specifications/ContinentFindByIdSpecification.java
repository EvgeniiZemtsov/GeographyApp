package com.eugeneze.study.dao.specifications;

import com.eugeneze.models.Continent;

public class ContinentFindByIdSpecification implements Specification<Continent> {
    private int id;

    public ContinentFindByIdSpecification(int id) {
        this.id = id;
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
        return "FROM Continent where id = " + id;
    }
}
