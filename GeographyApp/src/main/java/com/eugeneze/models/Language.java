package com.eugeneze.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс Язык
 */

public class Language {
    private int id;
    private String name;

    /**
     * Поле, хранящее информацию о количестве носителей языка
     */
    private int numberOfNativeSpeakers;

    /**
     * Поле, хранящее список стран, в которых используется этот язык
     */
    private final List<Country> countries = new ArrayList<>();

    public Language(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Метод добавляет страну в список стран, в которых используется этот язык
     */
    public void addCountry(Country country) {
        countries.add(country);
    }

    /**
     * Метод удаляет страну из списка стран, в которых используется этот язык
     */
    public void removeCountry(Country country) {
        countries.remove(country);
    }
}
