package com.eugeneze.models;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ContinentTest {
    Continent continent = new Continent(1, "Europe", 10180000);

    @Test
    void addCountryAddsCountryToTheList() {
        Country country = mock(Country.class);

        continent.addCountry(country);

        assertThat(continent).hasFieldOrPropertyWithValue("countries", Arrays.asList(country));
    }

    @Test
    void removeCountryRemovesCountryFromTheList() {
        Country country = mock(Country.class);

        continent.addCountry(country);
        continent.removeCountry(country);

        assertThat(continent).hasFieldOrPropertyWithValue("countries", Arrays.asList());
    }
}