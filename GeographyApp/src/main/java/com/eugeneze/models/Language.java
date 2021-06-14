package com.eugeneze.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс Язык
 */
@Entity
@Table(name = "languages")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private int id;
    @Column(name = "name")
    @JsonProperty
    private String name;

    /**
     * Поле, хранящее информацию о количестве носителей языка
     */
    @Column(name = "number_of_native_speakers")
    @JsonProperty
    private int numberOfNativeSpeakers;

    /**
     * Поле, хранящее список стран, в которых используется этот язык
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "language")

    private final List<Country> countries = new ArrayList<>();

//    public Language(int id, String name) {
//        this.id = id;
//        this.name = name;
//    }

    private Language(Builder builder) {
        this.name = builder.name;
        this.numberOfNativeSpeakers = builder.numberOfNativeSpeakers;
    }

    public Language() {
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

    public void setName(String name) {
        this.name = name;
    }

    public void setNumberOfNativeSpeakers(int numberOfNativeSpeakers) {
        this.numberOfNativeSpeakers = numberOfNativeSpeakers;
    }

    @JsonIgnore
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

        public Builder(String name) {
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

    @Override
    public String toString() {
        return "Language{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numberOfNativeSpeakers=" + numberOfNativeSpeakers +
                '}';
    }
}
