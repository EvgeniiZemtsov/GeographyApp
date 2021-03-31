package com.eugeneze.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс Валюта
 */

public class Currency {
    private int id;
    private String name;
    /**
     * Поле, хранящее трёхбуквенное обозначение валюты(EUR, USD и т.д.)
     */
    private String code;

    /**
     * Поле, хранящее список стран, в которых используется эта валюта
     */
    private final List<Country> countries = new ArrayList<>();

    public Currency(int id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    /**
     * Метод добавляет страну в список стран, в которых используется эта валюта
     */
    public void addCountry(Country country) {
        countries.add(country);
    }

    /**
     * Метод удаляет страну из списка стран, в которых используется эта валюта
     */
    public void removeCountry(Country country) {
        countries.remove(country);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Currency)) return false;

        Currency currency = (Currency) o;

        if (!name.equals(currency.name)) return false;
        return code.equals(currency.code);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + code.hashCode();
        return result;
    }
}
