package com.eugeneze.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CityTest {


    @Test
    void renameRenamesTheCity() throws Exception {
        Voting voting = mock(Voting.class);
        City city = new City.Builder(1, "Rome").build();
        city.setVoting(voting);

        when(voting.vote(city)).thenReturn(90);
        city.rename("Ancient Rome");

        assertThat(city).hasFieldOrPropertyWithValue("name", "Ancient Rome");
    }

    @Test
    void renameThrowsException() throws Exception {
        Voting voting = mock(Voting.class);
        City city = new City.Builder(1, "Rome").build();
        city.setVoting(voting);

        when(voting.vote(city)).thenReturn(10);

        Assertions.assertThrows(PopulationDidntSupportException.class, () -> city.rename("Ancient Rome"));

    }

    @Test
    void getIndependenceMakesCountryEqualsNull() throws Exception {
        Voting voting = mock(Voting.class);
        Country country = mock(Country.class);
        City city = new City.Builder(1, "Rome").setPopulation(5000000).setArea(700000).setCountry(country).build();

        country.addCity(city);
        city.setVoting(voting);
        when(voting.vote(city)).thenReturn(90);
        city.getIndependence();

        assertThat(city).hasFieldOrPropertyWithValue("country", null);
    }

    @Test
    void getIndependenceThrowsException() throws Exception {
        Voting voting = mock(Voting.class);
        Country country = mock(Country.class);
        City city = new City.Builder(1, "Rome").setPopulation(5000000).setArea(700000).setCountry(country).build();

        country.addCity(city);

        city.setVoting(voting);
        when(voting.vote(city)).thenReturn(10);

        Assertions.assertThrows(PopulationDidntSupportException.class, city::getIndependence);

    }

    @Test
    void addSightAddsToTheList() {
        Sight sight = mock(Sight.class);
        City city = new City.Builder(1, "Rome").setPopulation(5000000).setArea(700000).build();

        city.addSight(sight);

        assertThat(city).hasFieldOrPropertyWithValue("sights", Arrays.asList(sight));
    }

    @Test
    void removeSightRemovesFromTheList() {
        Sight sight = mock(Sight.class);
        City city = new City.Builder(1, "Rome").setPopulation(5000000).setArea(700000).build();

        city.addSight(sight);
        city.removeSight(sight);

        assertThat(city).hasFieldOrPropertyWithValue("sights", Arrays.asList());
    }

    @Test
    void expand() {
        City city = new City.Builder(1, "Rome").setPopulation(5000000).setArea(10000).build();

        city.expand(5000);

        assertThat(city).hasFieldOrPropertyWithValue("area", 15000);
    }
}