package com.eugeneze.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Абстрактный класс Географичческий объект
 */

public abstract class GeographicalObject {
    protected int id;
    protected String name;

    /**
     * Поле хранит список стран, на територии которых находится географический объект
     */
    protected final List<Country> countries = new ArrayList<>();

    public GeographicalObject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Метод добавляет страну в список стран, на територии которых находится географический объект
     */
    public void addCountry(Country country) {
        countries.add(country);
    }

    /**
     * Метод удаляет страну из списка стран, на територии которых находится географический объект
     */
    public void removeCountry(Country country) {
        countries.remove(country);
    }
}
