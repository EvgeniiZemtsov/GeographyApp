package com.eugeneze.study.dao.specifications;

public interface Specification<T> {

    boolean isSatisfiedBy(T t);
    String toSqlClause();
    String getHqlQuery();
}
