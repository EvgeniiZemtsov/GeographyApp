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

    public Sight(int id, String name) {
        this.id = id;
        this.name = name;
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
}
