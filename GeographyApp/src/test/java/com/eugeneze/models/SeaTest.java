package com.eugeneze.models;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class SeaTest {
    Sea sea = new Sea(1, "Mediterranean");

    @Test
    void addCountryAddsCountryToTheList() {
        Country country = mock(Country.class);

        sea.addCountry(country);

        assertThat(sea).hasFieldOrPropertyWithValue("countries", Arrays.asList(country));
    }

    @Test
    void removeCountryRemovesCountryFromTheList() {
        Country country = mock(Country.class);

        sea.addCountry(country);
        sea.removeCountry(country);

        assertThat(sea).hasFieldOrPropertyWithValue("countries", Arrays.asList());
    }

    @Test
    void addDimensionsAddsDimensions() {
        sea.addDimensions(2500000, 5267);

        assertThat(sea).hasFieldOrPropertyWithValue("area", 2500000)
                .hasFieldOrPropertyWithValue("maxDepth", 5267);
    }
}