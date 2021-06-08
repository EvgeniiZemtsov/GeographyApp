package com.eugeneze.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс Море
 */

@Entity
@Table(name = "seas")
public class Sea implements GeographicalObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    /**
     * Поля, определяющие размер моря
     */
    @Column(name = "area")
    private int area;
    @Column(name = "max_depth")
    private int maxDepth;

    /**
     * Поле хранит список стран, на територии которых находится географический объект
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "country_sea",
            joinColumns = @JoinColumn(name = "sea_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id"))
    private List<Country> countries = new ArrayList<>();

    public Sea() {
    }

    public Sea(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Метод устанавливает размеры моря
     */
    public void addDimensions(int area, int maxDepth) {
            this.area = area;
            this.maxDepth = maxDepth;
    }

    public List<Country> getCountries() {
        return countries;
    }

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
        return "Sea{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", area=" + area +
                ", maxDepth=" + maxDepth +
                '}';
    }
}
