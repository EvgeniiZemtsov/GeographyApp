package com.eugeneze.dao.specifications;

import com.eugeneze.models.City;
import com.eugeneze.models.Currency;

public class CurrencyFindAllSpecification implements Specification<Currency> {
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
        return "FROM Currency";
    }
}
