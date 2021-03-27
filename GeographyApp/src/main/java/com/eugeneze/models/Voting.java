package com.eugeneze.models;

/**
 * Класс, ответственный за организацию голосования (в городе или в стране) и подсчёт результатов
 */

public class Voting {
    /**
     * Метод организует голосование в стране и подсчитывает результаты
     * @param country Страна, в которой необходимо произвести голосование
     * @return Результаты голосования
     */

    public int vote(Country country) {
        double result = 0;
        if (Math.random() > 0.5) {
            if (country.getPopulation() > 100000000) {
                result = 75 * Math.random() - 4;
            } else {
                result = 80 * Math.random() + 7;
            }
        } else {
            if (country.getArea() > 500000) {
                result = 70 * Math.random() - 6;
            } else {
                result = 85 * Math.random() + 3;
            }
        }
        return (int)result;
    }

    /**
     * Метод организует голосование в городе и подсчитывает результаты
     * @param city Город, в котором необходимо произвести голосование
     * @return Результаты голосования
     */
    public int vote(City city) {
        double result = 0;
        if (Math.random() > 0.5) {
            if (city.getPopulation() > 1000000) {
                result = 70 * Math.random();
            } else {
                result = 85 * Math.random() + 5;
            }
        } else {
            if (city.getArea() > 2500) {
                result = 65 * Math.random() + 7;
            } else {
                result = 90 * Math.random() - 3;
            }
        }
        return (int)result;
    }
}
