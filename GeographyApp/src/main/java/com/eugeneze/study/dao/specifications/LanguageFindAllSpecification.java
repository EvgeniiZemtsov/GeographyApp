package com.eugeneze.study.dao.specifications;

import com.eugeneze.models.Language;

public class LanguageFindAllSpecification implements Specification<Language> {
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
        return "FROM Language";
    }
}
