package com.eugeneze.dao.specifications;

import com.eugeneze.models.Country;

import java.lang.reflect.Field;

public class CountryFindByNameSpecification implements Specification<Country> {
    private String name;

    public CountryFindByNameSpecification(String name) {
        this.name = name;
    }

//    USE REFLECTION AGAIN TO GET A NAME
    @Override
    public boolean isSatisfiedBy(Country country) {
        String countryName = null;
        try {
            Field countryNameField = country.getClass().getDeclaredField("name");
            countryNameField.setAccessible(true);

            countryName = (String) countryNameField.get(country);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }

        return name.equals(countryName);
    }

    @Override
    public String getHqlQuery() {
        return "FROM Country where name = '" + name + "'";
    }

    public String toSqlClause() {
        return String.format("country_general.name = '%s';", name);
    }


}
