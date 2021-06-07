package com.eugeneze.models;

import javax.persistence.*;

/**
 * Класс "Достопримечательности"
 */

@Entity
@Table(name = "sights")
public class Sight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    /**
     * Город, в котором находится достопричечательность
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    public Sight(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private Sight(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public Sight() {

    }

    /**
     * Метод приписывает достопримечательность к городу
     */
    public void assignToTheCity(City city) {
        this.city = city;
        city.addSight(this);
    }

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

    @Override
    public String toString() {
        return "Sight{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
