package com.eugeneze.study.dao.specifications;

import com.eugeneze.models.Sea;

public class SeaFindByNameSpecification implements Specification<Sea> {
    private String name;

    public SeaFindByNameSpecification(String name) {
        this.name = name;
    }

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
        return "FROM Sea where name = '" + name + "'";
    }
}
