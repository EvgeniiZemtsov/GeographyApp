package com.eugeneze.dao.specifications;

import com.eugeneze.models.City;
import com.eugeneze.models.Mountain;

public class MountainFindAllSpecification implements Specification<Mountain> {
    @Override
    public boolean isSatisfiedBy(Mountain mountain) {
        return true;
    }

    @Override
    public String toSqlClause() {
        return null;
    }

    @Override
    public String getHqlQuery() {
        return "FROM Mountain";
    }
}
