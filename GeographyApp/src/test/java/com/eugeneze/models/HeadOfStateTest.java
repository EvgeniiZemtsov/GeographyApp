package com.eugeneze.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class HeadOfStateTest {

    HeadOfState headOfState = new HeadOfState(1, "Sergio", "Mattarella");

    @Test
    void addExtraInformationAddsExtraInformation() {
        Country country = mock(Country.class);

        headOfState.addExtraInformation(LocalDate.of(1941, 7, 23), country, "President");

        assertThat(headOfState).hasFieldOrPropertyWithValue("dateOfBirth", LocalDate.of(1941, 7, 23))
                .hasFieldOrPropertyWithValue("workPlace", country)
                .hasFieldOrPropertyWithValue("title", "President");
    }

    @Test
    void takePartInTheElection() {
    }

    @Test
    void leaveThePostOfHeadOfStateClearsTheWorkPlace() {
        Country country = mock(Country.class);

        headOfState.addExtraInformation(null, country, null);
        headOfState.leaveThePostOfHeadOfState();

        assertThat(headOfState).hasFieldOrPropertyWithValue("workPlace", null);
    }
}