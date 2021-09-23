package com.eugeneze.study.dao.specifications;

import com.eugeneze.models.Ocean;

public class OceanFindByNameSpecification implements Specification<Ocean> {
    private String name;

    public OceanFindByNameSpecification(String name) {
        this.name = name;
    }

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
        return "FROM Ocean where name = '" + name + "'";
    }
}
