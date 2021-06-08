package com.eugeneze.dao.specifications;

import com.eugeneze.models.HeadOfState;

public class HeadOfStateFindAllSpecification implements Specification<HeadOfState> {
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
        return "FROM HeadOfState";
    }
}
