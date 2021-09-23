package com.eugeneze.study.dao.specifications;

import com.eugeneze.models.Currency;

public class CurrencyFindByNameSpecification implements Specification<Currency> {
    private String name;

    public CurrencyFindByNameSpecification(String name) {
        this.name = name;
    }

    @Override
    public boolean isSatisfiedBy(Currency currency) {
        return true;
    }

    @Override
    public String toSqlClause() {
        return null;
    }

    @Override
    public String getHqlQuery() {
        return "FROM Currency where name = '" + name + "'";
    }
}
