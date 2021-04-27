package com.eugeneze.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class VotingTest {
    Voting voting = new Voting();

    @Test
    void voteCountryReturnsANumber() {
        Country country = mock(Country.class);
        when(country.getArea()).thenReturn(242000);
        when(country.getPopulation()).thenReturn(66000000);

        country.setVoting(voting);
        int result = voting.vote(country);
        assertFalse(result == 0);
    }

//
//    @Test
//    void voteCityReturnsANumber() {
//        City city = mock(City.class);
//        when(city.getArea()).thenReturn(242000);
//        when(city.getPopulation()).thenReturn(66000000);
//
//        city.setVoting(voting);
//        int result = voting.vote(city);
//        assertFalse(result == 0);
//    }
}