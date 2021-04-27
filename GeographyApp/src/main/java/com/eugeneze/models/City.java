package com.eugeneze.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс Город
 */

public class City {
    private int id;
    private String name;
    private Country country;
    private int area;
    private int population;
    /**
     * Переменная, хранящая список достопримечательностей, находящихся в городе
     */
    private List<Sight> sights = new ArrayList<>();

    /**
     * Переменная, содержащая объект, отвечающий за организацию голосования и подсчёт результатов
     */
    private Voting voting;

    private City(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.country = builder.country;
        this.area = builder.area;
        this.population = builder.population;

    }

    /**
     * Метод меняет название города
     */
    public void rename(String newName) throws Exception {
        if (voting.vote(this) > 80) {
            this.name = newName;
        } else {
            throw new PopulationDidntSupportException("The proposal wasn't approved by the population.");
        }
    }

    /**
     * Метод даёт городу независимость
     */
    public void getIndependence() throws Exception {
        if (voting.vote(this) > 51) {
            country.removeCity(this);
            country = null;
        } else {
            throw new PopulationDidntSupportException("The proposal wasn't approved by the population.");
        }
    }

    /**
     * Метод добавляет достопримечательность в список достопримечательностей, находящихся в городе
     */
    public void addSight(Sight sight) {
        sights.add(sight);
    }

    /**
     * Метод удаляет достопримечательность из списка достопримечательностей, находящихся в городе
     */
    public void removeSight(Sight sight) {
        sights.remove(sight);
    }

    /**
     * Метод расширяет территорию города
     */
    public void expand(int addArea) {
        area += addArea;
    }

    public void setVoting(Voting voting) {
        this.voting = voting;
    }

    int getArea() {
        return area;
    }

    int getPopulation() {
        return population;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;

        City city = (City) o;

        if (area != city.area) return false;
        if (population != city.population) return false;
        if (!name.equals(city.name)) return false;
        return country != null ? country.equals(city.country) : city.country == null;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + area;
        result = 31 * result + population;
        return result;
    }

    public Object[] getObjects() {
        return new Object[] {
                id,
                name,
                country,
                area,
                population,
                sights
        };
    }

    public static final class Builder {
        private int id;
        private String name;
        private Country country;
        private int area;
        private int population;

        public Builder(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public Builder setCountry(Country country) {
            this.country = country;
            return this;
        }

        public Builder setArea(int area) {
            this.area = area;
            return this;
        }

        public Builder setPopulation(int population) {
            this.population = population;
            return this;
        }

        public City build() {
            return new City(this);
        }
    }

}
