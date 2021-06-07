package com.eugeneze.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс Валюта
 */

@Entity
@Table(name = "currency")
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    /**
     * Поле, хранящее трёхбуквенное обозначение валюты(EUR, USD и т.д.)
     */
    @Column(name = "currency_code")
    private String code;

    /**
     * Поле, хранящее список стран, в которых используется эта валюта
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "currency")
    private final List<Country> countries = new ArrayList<>();

    public Currency(int id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public Currency() {
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

    public Object[] getObjects() {
        return new Object[] {
                id,
                name,
                code,
                countries
        };
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

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
