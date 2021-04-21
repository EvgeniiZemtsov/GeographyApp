package com.eugeneze.models;

/**
 * Класс "Достопримечательности"
 */
public class Sight {
    private int id;
    private String name;
    /**
     * Город, в котором находится достопричечательность
     */
    private City city;

//    public Sight(int id, String name) {
//        this.id = id;
//        this.name = name;
//    }

    private Sight(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    /**
     * Метод ограничивает доступ к достопримечательности для посетителей
     */
    public void restrictAccessForVisitors() {

    }

    /**
     * Метод приписывает достопримечательность к городу
     */
    public void assignToTheCity(City city) {
        this.city = city;
        city.addSight(this);
    }

//    public String declareAsACulturalMonument() {
//        String result = null;
//        if (Voting.vote(city) > 75) {
//            result = "Now it's cultural monument. Congratulation!!!";
//        } else {
//            result = "The proposal wasn't approved by the population.";
//        }
//        return result;
//    }

    public Object[] getObjects() {
        return new Object[] {
                id,
                name,
                city
        };
    }

    public static final class Builder {
        private int id;
        private String name;
        private City city;

        public Builder(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public Builder setCity(City city) {
            this.city = city;
            return this;
        }

        public Sight build() {
            return new Sight(this);
        }
    }
}
