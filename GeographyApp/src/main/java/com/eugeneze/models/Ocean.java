package com.eugeneze.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс Океан
 */

@Entity
@Table(name = "oceans")
public class Ocean implements GeographicalObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private int id;
    @Column(name = "name")
    @JsonProperty
    private String name;
    /**
     * Поля, определяющие размер моря
     */
    @Column(name = "area")
    @JsonProperty
    private int area;
    @Column(name = "max_depth")
    @JsonProperty
    private int maxDepth;

    /**
     * Поле хранит список стран, на територии которых находится географический объект
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "country_ocean",
            joinColumns = @JoinColumn(name = "ocean_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id"))
    private List<Country> countries = new ArrayList<>();

    public Ocean(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Ocean() {
    }

    public Ocean(String name) {
        this.name = name;
    }

    /**
     * Метод устанавливает размеры моря
     */
//    public void addDimensions(int area, int maxDepth) {
//            this.area = area;
//            this.maxDepth = maxDepth;
//    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
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
                maxDepth,
                countries
        };
    }

    @Override
    public String toString() {
        return "Ocean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", area=" + area +
                ", maxDepth=" + maxDepth +
                '}';
    }
}
