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

//    public Language(int id, String name) {
//        this.id = id;
//        this.name = name;
//    }

    private Language(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.numberOfNativeSpeakers = builder.numberOfNativeSpeakers;
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

    public Object[] getObjects() {
        return new Object[] {
                id,
                name,
                numberOfNativeSpeakers,
                countries
        };
    }

    public static final class Builder {
        private int id;
        private String name;
        private int numberOfNativeSpeakers;

        public Builder(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public Builder setNumberOfNativeSpeakers(int numberOfNativeSpeakers) {
            this.numberOfNativeSpeakers = numberOfNativeSpeakers;
            return this;
        }

        public Language build() {
            return new Language(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Language)) return false;

        Language language = (Language) o;

        if (numberOfNativeSpeakers != language.numberOfNativeSpeakers) return false;
        return name.equals(language.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + numberOfNativeSpeakers;
        return result;
    }
}
