package com.eugeneze.models;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CurrencyTest {
    Currency currency = new Currency(1, "Euro", "EUR");
    Country country = mock(Country.class);

    @Test
    void addCountryAddsCountyToTheList() {
        currency.addCountry(country);

        assertThat(currency).hasFieldOrPropertyWithValue("countries", Arrays.asList(country));
    }

    @Test
    void removeCountryRemovesCountry() {
        currency.addCountry(country);
        currency.removeCountry(country);

        assertThat(currency).hasFieldOrPropertyWithValue("countries", Arrays.asList());
    }
}