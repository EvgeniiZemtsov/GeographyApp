package com.eugeneze.dao.specifications;

import com.eugeneze.models.Ocean;

public class OceanFindAllSpecification implements Specification<Ocean> {
    @Override
    public boolean isSatisfiedBy(Ocean ocean) {
        return true;
    }

    @Override
    public String toSqlClause() {
        return null;
    }

    @Override
    public String getHqlQuery() {
        return "FROM Ocean";
    }
}
