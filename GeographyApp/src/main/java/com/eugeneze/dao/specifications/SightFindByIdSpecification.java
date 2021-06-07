package com.eugeneze.dao.specifications;

import com.eugeneze.models.Sight;

public class SightFindByIdSpecification implements Specification<Sight> {
    private int id;

    public SightFindByIdSpecification(int id) {
        this.id = id;
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
        return "FROM Sight where id = " + id;
    }
}
