package com.eugeneze.study.dao.specifications;

import com.eugeneze.models.Sea;

public class SeaFindAllSpecification implements Specification<Sea> {
    @Override
    public boolean isSatisfiedBy(Sea sea) {
        return true;
    }

    @Override
    public String toSqlClause() {
        return null;
    }

    @Override
    public String getHqlQuery() {
        return "FROM Sea";
    }
}
