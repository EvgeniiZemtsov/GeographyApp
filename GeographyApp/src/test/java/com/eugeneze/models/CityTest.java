package com.eugeneze.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CityTest {

    City city  = new City(1, "Rome");

    @Test
    void addExtraInformationAddsInformation() {
        Country country = mock(Country.class);

        city.addExtraInformation(700000, 5000000, country);

        assertThat(city).hasFieldOrPropertyWithValue("area", 700000)
                .hasFieldOrPropertyWithValue("population", 5000000)
                .hasFieldOrPropertyWithValue("country", country);
    }

    @Test
    void renameRenamesTheCity() throws Exception {
        Voting voting = mock(Voting.class);
        city.setVoting(voting);

        when(voting.vote(city)).thenReturn(90);
        city.rename("Ancient Rome");

        assertThat(city).hasFieldOrPropertyWithValue("name", "Ancient Rome");
    }

    @Test
    void renameThrowsException() throws Exception {
        Voting voting = mock(Voting.class);
        city.setVoting(voting);

        when(voting.vote(city)).thenReturn(10);

        Assertions.assertThrows(PopulationDidntSupportException.class, () -> city.rename("Ancient Rome"));

    }



    @Test
    void getIndependenceMakesCountryEqualsNull() throws Exception {
        Voting voting = mock(Voting.class);
        Country country = mock(Country.class);

        country.addCity(city);
        city.addExtraInformation(700000, 5000000, country);
        city.setVoting(voting);
        when(voting.vote(city)).thenReturn(90);
        city.getIndependence();

        assertThat(city).hasFieldOrPropertyWithValue("country", null);
    }

    @Test
    void getIndependenceThrowsException() throws Exception {
        Voting voting = mock(Voting.class);
        Country country = mock(Country.class);

        country.addCity(city);
        city.addExtraInformation(700000, 5000000, country);
        city.setVoting(voting);
        when(voting.vote(city)).thenReturn(10);

        Assertions.assertThrows(PopulationDidntSupportException.class, () -> city.getIndependence());

    }



    @Test
    void addSightAddsToTheList() {
        Sight sight = mock(Sight.class);

        city.addSight(sight);

        assertThat(city).hasFieldOrPropertyWithValue("sights", Arrays.asList(sight));
    }

    @Test
    void removeSightRemovesFromTheList() {
        Sight sight = mock(Sight.class);

        city.addSight(sight);
        city.removeSight(sight);

        assertThat(city).hasFieldOrPropertyWithValue("sights", Arrays.asList());
    }

    @Test
    void expand() {
        city.addExtraInformation(10000, 5000000, null);

        city.expand(5000);

        assertThat(city).hasFieldOrPropertyWithValue("area", 15000);
    }
}