package com.eugeneze.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Абстрактный класс Географичческий объект
 */

public interface GeographicalObject {

    /**
     * Поле хранит список стран, на територии которых находится географический объект
     */
    List<Country> countries = new ArrayList<>();

    /**
     * Метод добавляет страну в список стран, на територии которых находится географический объект
     */
    default void addCountry(Country country) {
        countries.add(country);
    }

    /**
     * Метод удаляет страну из списка стран, на територии которых находится географический объект
     */
    default void removeCountry(Country country) {
        countries.remove(country);
    }
}
