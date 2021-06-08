package com.eugeneze.dao.specifications;

import com.eugeneze.models.HeadOfState;

public class HeadOfStateFindByIdSpecification implements Specification<HeadOfState> {
    private int id;

    public HeadOfStateFindByIdSpecification(int id) {
        this.id = id;
    }

    @Override
    public boolean isSatisfiedBy(HeadOfState headOfState) {
        return true;
    }

    @Override
    public String toSqlClause() {
        return null;
    }

    @Override
    public String getHqlQuery() {
        return "FROM HeadOfState where id = " + id;
    }
}
