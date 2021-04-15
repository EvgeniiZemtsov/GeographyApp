package com.eugeneze.dao;

import com.eugeneze.models.Country;

import java.lang.reflect.Field;

public class FindByIdSpecification extends AbstractSpecification<Country> {
    private int id;

    public FindByIdSpecification(int id) {
        this.id = id;
    }

//    USE REFLECTION TO GET ID
    @Override
    public boolean isSatisfiedBy(Country country) {

        int countryId = -1;

        try {
            Field countryIdField = country.getClass().getDeclaredField("id");
            countryIdField.setAccessible(true);
            countryId = countryIdField.getInt(country);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }

        return id == countryId;
    }

    @Override
    public String toSqlClause() {
        return String.format(" id = %d ", id);
    }
}
