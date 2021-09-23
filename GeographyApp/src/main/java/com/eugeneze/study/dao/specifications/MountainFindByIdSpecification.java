package com.eugeneze.study.dao.specifications;

import com.eugeneze.models.Mountain;

public class MountainFindByIdSpecification implements Specification<Mountain> {
    private int id;

    public MountainFindByIdSpecification(int id) {
        this.id = id;
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
        return "FROM Mountain where id = " + id;
    }
}
