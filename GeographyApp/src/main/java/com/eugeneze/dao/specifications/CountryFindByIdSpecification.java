package com.eugeneze.dao.specifications;

import com.eugeneze.models.Country;

import java.lang.reflect.Field;

public class CountryFindByIdSpecification implements Specification<Country> {
    private int id;

    public CountryFindByIdSpecification(int id) {
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
    public String getHqlQuery() {
        return "FROM Country where id = " + id;
    }


    public String toSqlClause() {
        return "country_general.country_id = " + id + ";";
//        return String.format("id = %d ", id);
    }
}
