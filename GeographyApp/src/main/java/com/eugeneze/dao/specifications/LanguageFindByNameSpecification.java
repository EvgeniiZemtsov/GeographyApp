package com.eugeneze.dao.specifications;

import com.eugeneze.models.City;
import com.eugeneze.models.Language;

public class LanguageFindByNameSpecification implements Specification<Language> {
    private String name;

    public LanguageFindByNameSpecification(String name) {
        this.name = name;
    }

    @Override
    public boolean isSatisfiedBy(Language language) {
        return true;
    }

    @Override
    public String toSqlClause() {
        return null;
    }

    @Override
    public String getHqlQuery() {
        return "FROM Language where name = '" + name + "'";
    }
}
