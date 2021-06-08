package com.eugeneze.dao.specifications;

import com.eugeneze.models.HeadOfState;

public class HeadOfStateFindByNameSpecification implements Specification<HeadOfState> {
    private String lastName;

    public HeadOfStateFindByNameSpecification(String lastName) {
        this.lastName = lastName;
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
        return "FROM HeadOfState where last_name = '" + lastName + "'";
    }
}
