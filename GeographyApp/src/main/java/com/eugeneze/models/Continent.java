package com.eugeneze.models;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "continents")
public class Continent implements GeographicalObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "area")
    private int area;

    /**
     * Поле хранит список стран, на територии которых находится географический объект
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "continent")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Country> countries = new ArrayList<>();

    public Continent() {
    }

    public Continent(int id, String name, int area) {
        this.id = id;
        this.name = name;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public Object[] getObjects() {
        return new Object[] {
                id,
                name,
                area,
                countries
        };
    }

    @Override
    public String toString() {
        return "Continent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", area=" + area +
//                ", countries=" + countries +
                '}';
    }
}
