package com.eugeneze.models;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class MountainTest {
    Mountain mountain = new Mountain(1, "Everest");

    @Test
    void addCountryAddsCountryToTheList() {
        Country country = mock(Country.class);

        mountain.addCountry(country);

        assertThat(mountain).hasFieldOrPropertyWithValue("countries", Arrays.asList(country));
    }

    @Test
    void removeCountryRemovesCountryFromTheList() {
        Country country = mock(Country.class);

        mountain.addCountry(country);
        mountain.removeCountry(country);

        assertThat(mountain).hasFieldOrPropertyWithValue("countries", Arrays.asList());
    }

    @Test
    void addHeightAddsHeight() {
        mountain.addHeight(8848);

        assertThat(mountain).hasFieldOrPropertyWithValue("height", 8848);
    }
}