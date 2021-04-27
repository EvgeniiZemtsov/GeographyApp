package com.eugeneze.dao;

public interface Specification<T> {

    boolean isSatisfiedBy(T t);
    String toSqlClause();
}
