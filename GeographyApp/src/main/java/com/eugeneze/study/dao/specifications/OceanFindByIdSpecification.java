package com.eugeneze.study.dao.specifications;

import com.eugeneze.models.Ocean;

public class OceanFindByIdSpecification implements Specification<Ocean> {
    private int id;

    public OceanFindByIdSpecification(int id) {
        this.id = id;
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
        return "FROM Ocean where id = " + id;
    }
}
