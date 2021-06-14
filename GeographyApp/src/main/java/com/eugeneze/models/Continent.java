package com.eugeneze.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "continents")
public class Continent implements GeographicalObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Integer id;
    @Column(name = "name")
    @JsonProperty
    private String name;
    @Column(name = "area")
    @JsonProperty
    private Integer area;

    /**
     * Поле хранит список стран, на територии которых находится географический объект
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "continent")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    private List<Country> countries = new ArrayList<>();

    public Continent() {
    }

    public Continent(String name, int area) {
        this.name = name;
        this.area = area;
    }

    public Continent(int id, String name, int area) {
        this.id = id;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArea(int area) {
        this.area = area;
    }

    @JsonIgnore
    public List<Country> getCountries() {
        return countries;
    }

    @JsonIgnore
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
