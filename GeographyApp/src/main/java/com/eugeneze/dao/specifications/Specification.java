package com.eugeneze.dao.specifications;

public interface Specification<T> {

    boolean isSatisfiedBy(T t);
    String toSqlClause();
    String getHqlQuery();
}
