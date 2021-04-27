package com.eugeneze.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class HeadOfStateTest {

    @Test
    void takePartInTheElection() {
    }

    @Test
    void leaveThePostOfHeadOfStateClearsTheWorkPlace() {
        Country country = mock(Country.class);
        HeadOfState headOfState = new HeadOfState.Builder(1, "Sergio", "Mattarella").
                setWorkPlace(country).build();

        headOfState.leaveThePostOfHeadOfState();

        assertThat(headOfState).hasFieldOrPropertyWithValue("workPlace", null);
    }
}