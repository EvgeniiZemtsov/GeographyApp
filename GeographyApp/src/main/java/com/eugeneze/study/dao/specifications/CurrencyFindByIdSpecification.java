package com.eugeneze.study.dao.specifications;

import com.eugeneze.models.Currency;

public class CurrencyFindByIdSpecification implements Specification<Currency> {
    private int id;

    public CurrencyFindByIdSpecification(int id) {
        this.id = id;
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
        return "FROM Currency where id = " + id;
    }
}
