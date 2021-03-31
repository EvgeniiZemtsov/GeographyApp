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

    public City(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Метод добавляет дополнительную информацию о городе
     */
    public void addExtraInformation(int area, int population, Country country) {
        this.area = area;
        this.population = population;
        this.country = country;
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

}
