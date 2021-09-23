package com.eugeneze.study.dao.specifications;

import com.eugeneze.models.Language;

public class LanguageFindByIdSpecification implements Specification<Language> {
    private int id;

    public LanguageFindByIdSpecification(int id) {
        this.id = id;
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
        return "FROM Language where id = " + id;
    }
}
