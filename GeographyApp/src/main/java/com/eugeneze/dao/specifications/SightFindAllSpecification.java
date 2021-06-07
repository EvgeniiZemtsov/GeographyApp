package com.eugeneze.dao.specifications;

import com.eugeneze.models.City;
import com.eugeneze.models.Sight;

public class SightFindAllSpecification implements Specification<Sight> {
    @Override
    public boolean isSatisfiedBy(Sight sight) {
        return true;
    }

    @Override
    public String toSqlClause() {
        return null;
    }

    @Override
    public String getHqlQuery() {
        return "FROM Sight";
    }
}
