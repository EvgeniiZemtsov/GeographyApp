package com.eugeneze.service;

import com.eugeneze.models.Country;
import com.eugeneze.models.Ocean;
import com.eugeneze.models.Sea;

public class Application {
    public static void main(String[] args) {
        Sea sea = new Sea(1, "Medit");
        System.out.println(sea);
        Ocean ocean = new Ocean(2, "Pacific");
        System.out.println("Before method");
        ocean.addDimensions(453, 322);
        System.out.println("The program is running after method!!!");

    }
}
