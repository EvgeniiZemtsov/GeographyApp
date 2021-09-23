package com.eugeneze.study.dao.specifications;

import com.eugeneze.models.Mountain;

public class MountainFindByNameSpecification implements Specification<Mountain> {
    private String name;

    public MountainFindByNameSpecification(String name) {
        this.name = name;
    }

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
        return "FROM Mountain where name = '" + name + "'";
    }
}
