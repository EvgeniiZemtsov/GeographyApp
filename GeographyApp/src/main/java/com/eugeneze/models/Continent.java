package com.eugeneze.models;

import java.util.List;

public class Continent extends GeographicalObject {
    private int area;

    public Continent(int id, String name, int area) {
        super(id, name);
        this.area = area;
    }

}
