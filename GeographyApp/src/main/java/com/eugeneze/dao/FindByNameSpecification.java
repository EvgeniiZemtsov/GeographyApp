package com.eugeneze.dao;

import com.eugeneze.models.Country;

import java.lang.reflect.Field;

public class FindByNameSpecification extends AbstractSpecification<Country> {
    private String name;

    public FindByNameSpecification(String name) {
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
    public String toSqlClause() {
        return String.format(" name = %s ", name);
    }


}
