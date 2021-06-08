package com.eugeneze.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс Гора
 */

@Entity
@Table(name = "mountains")
public class Mountain implements GeographicalObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;

    /**
     * Поле, определяющее высоту горы
     */
    @Column(name = "height")
    private int height;

    /**
     * Поле хранит список стран, на територии которых находится географический объект
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "country_mountain",
            joinColumns = @JoinColumn(name = "mountain_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id"))
    private List<Country> countries = new ArrayList<>();

    public Mountain() {
    }

    public Mountain(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Метод устанавливает высоту горы
     */
    public void addHeight(int height) {
            this.height = height;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public Object[] getObjects() {
        return new Object[] {
                id,
                name,
                height,
                countries
        };
    }

    @Override
    public String toString() {
        return "Mountain{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", height=" + height +
                '}';
    }
}
