package com.eugeneze.models;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class OceanTest {
    Ocean ocean = new Ocean(1, "Pacific");

    @Test
    void addCountryAddsCountryToTheList() {
        Country country = mock(Country.class);

        ocean.addCountry(country);

        assertThat(ocean).hasFieldOrPropertyWithValue("countries", Arrays.asList(country));
    }

    @Test
    void removeCountryRemovesCountryFromTheList() {
        Country country = mock(Country.class);

        ocean.addCountry(country);
        ocean.removeCountry(country);

        assertThat(ocean).hasFieldOrPropertyWithValue("countries", Arrays.asList());
    }

    @Test
    void addDimensionsAddsDimensions() {
        ocean.addDimensions(165200000, 10911);

        assertThat(ocean).hasFieldOrPropertyWithValue("area", 165200000)
                .hasFieldOrPropertyWithValue("maxDepth", 10911);
    }
}