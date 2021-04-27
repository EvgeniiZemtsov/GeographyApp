package com.eugeneze.models;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class LanguageTest {

    @Test
    void addCountry() {
        Country country = mock(Country.class);
        Language language = new Language.Builder(1, "English").build();

        language.addCountry(country);

        assertThat(language).hasFieldOrPropertyWithValue("countries", Arrays.asList(country));
    }

    @Test
    void removeCountry() {
        Country country = mock(Country.class);
        Language language = new Language.Builder(1, "English").build();

        language.addCountry(country);
        language.removeCountry(country);

        assertThat(language).hasFieldOrPropertyWithValue("countries", Arrays.asList());
    }
}