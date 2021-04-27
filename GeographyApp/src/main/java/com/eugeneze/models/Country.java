package com.eugeneze.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс Страна
 */

public class Country {
    private int id;
    private String name;
    private int population;
    private int area;

    /**
     * Поле, хранящее информацию о государственной валюте
     */
    private Currency currency;

    /**
     * Поле, хранящее информацию о главе государства
     */
    private HeadOfState headOfState;

    /**
     * Поле, хранящее информацию о государственном языке
     */
    private Language language;
    private Continent continent;
    private City capital;

    /**
     * Переменная, содержащая объект, отвечающий за организацию голосования и подсчёт результатов
     */
    private Voting voting;


    /**
     * Поле, хранящее список городов, находящихся в стране
     */
    private final List<City> cities = new ArrayList<>();

    /**
     * Поле, хранящее список гор, находящихся в стране
     */
    private final List<Mountain> mountains = new ArrayList<>();

    /**
     * Поле, хранящее список морей, находящихся в стране
     */
    private final List<Sea> seas = new ArrayList<>();

    /**
     * Поле, хранящее список океанов, находящихся в стране
     */
    private final List<Ocean> oceans = new ArrayList<>();

//    public Country(int id, String name) {
//        this.id = id;
//        this.name = name;
//    }

    private Country(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.population = builder.population;
        this.area = builder.area;
        this.currency = builder.currency;
        this.headOfState = builder.headOfState;
        this.language = builder.language;
        this.continent = builder.continent;
        this.capital = builder.capital;
        this.voting = builder.voting;

    }

    /**
     * Метод переизбирает кандидата на должность главы государства
     */
    public void reelectTheHeadOfState(HeadOfState headOfState) throws Exception {
        if (voting.vote(this) > 50) {
            this.headOfState.leaveThePostOfHeadOfState();
            this.headOfState = headOfState;
        } else {
            throw new PopulationDidntSupportException("This candidate wasn't approved by the population.");
        }
    }

    /**
     * Метод меняет государственную валюту
     */
    public void changeCurrency(Currency currency) {
        this.currency.removeCountry(this);
        this.currency = currency;
        currency.addCountry(this);
    }

    /**
     * Метод меняет государственный язык
     */
    public void changeOfficialLanguage(Language language) {
        this.language.removeCountry(this);
        this.language = language;
        language.addCountry(this);
    }


    /**
     * Метод переносит столицу государства в другой город
     */
    public void moveTheCapital(City newCapital) throws Exception {
        if (cities.contains(newCapital)) {
            if (voting.vote(this) > 80) {
                capital = newCapital;
            }
        } else {
            throw new PopulationDidntSupportException("You can't move the capital to another country!!!");
        }
    }

    /**
     * Метод продаёт город другой стране
     */
    public void sellTheCity(City city, Country newOwner) throws Exception {
        if (!cities.contains(city)) {
            throw new CityNotInTheListException("City doesn't belong to this country. You can't sell it.");
        } else {
            newOwner.addCity(city);
            removeCity(city);
        }
    }

    public void setVoting(Voting voting) {
        this.voting = voting;
    }
    public List<HeadOfState> getListOfExLeaders() {
        return null;
    }

    /**
     * Метод добавляет город в список городов, находящихся в стране
     */
    public void addCity(City city) {
        cities.add(city);
    }

    /**
     * Метод удаляет город из списка городов, находящихся в стране
     */
    public void removeCity(City city) {
        cities.remove(city);
    }

    /**
     * Метод добавляет гору, океан или море, в список объектов, находящихся в стране
     */
    public void addGeoObject(GeographicalObject geographicalObject) {
        if (geographicalObject instanceof Mountain) {
            mountains.add((Mountain)geographicalObject);
        } else if (geographicalObject instanceof Ocean) {
            oceans.add((Ocean)geographicalObject);
        } else if (geographicalObject instanceof Sea) {
            seas.add((Sea)geographicalObject);
        }
    }

    /**
     * Метод удаляет гору, океан или море, из списка объектов, находящихся в стране
     */
    public void removeGeoObject(GeographicalObject geographicalObject) {
        if (geographicalObject instanceof Mountain) {
            mountains.remove(geographicalObject);
        } else if (geographicalObject instanceof Ocean) {
            oceans.remove(geographicalObject);
        } else if (geographicalObject instanceof Sea) {
            seas.remove(geographicalObject);
        }
    }

    int getPopulation() {
        return population;
    }

    int getArea() {
        return area;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country)) return false;

        Country country = (Country) o;

        if (population != country.population) return false;
        if (area != country.area) return false;
        return name.equals(country.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + population;
        result = 31 * result + area;
        return result;
    }

    public Object[] getObjects() {
        return new Object[] {
                id,
                name,
                population,
                area,
                currency,
                headOfState,
                language,
                continent,
                capital,
                cities,
                mountains,
                oceans,
                seas
        };
    }

    public static final class Builder {
        private int id;
        private String name;
        private int population;
        private int area;
        private Currency currency;
        private HeadOfState headOfState;
        private Language language;
        private Continent continent;
        private City capital;
        private Voting voting;

        public Builder(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public Builder setPopulation(int population) {
            this.population = population;
            return this;
        }

        public Builder setArea(int area) {
            this.area = area;
            return this;
        }

        public Builder setCurrency(Currency currency) {
            this.currency = currency;
            return this;
        }

        public Builder setHeadOfState(HeadOfState headOfState) {
            this.headOfState = headOfState;
            return this;
        }

        public Builder setLanguage(Language language) {
            this.language = language;
            return this;
        }

        public Builder setContinent(Continent continent) {
            this.continent = continent;
            return this;
        }

        public Builder setCapital(City capital) {
            this.capital = capital;
            return this;
        }

        public Builder setVoting(Voting voting) {
            this.voting = voting;
            return this;
        }

        public Country build() {
            return new Country(this);
        }
    }


}
