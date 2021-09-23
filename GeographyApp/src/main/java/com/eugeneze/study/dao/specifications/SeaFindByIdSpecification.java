package com.eugeneze.study.dao.specifications;

import com.eugeneze.models.Sea;

public class SeaFindByIdSpecification implements Specification<Sea> {
    private int id;

    public SeaFindByIdSpecification(int id) {
        this.id = id;
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
        return "FROM Sea where id = " + id;
    }
}
