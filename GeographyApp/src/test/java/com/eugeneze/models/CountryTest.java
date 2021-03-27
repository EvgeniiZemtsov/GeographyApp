package com.eugeneze.models;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


class CountryTest {

    Country country = new Country(1, "Australia");

    @Test
    public void addGeographicalInformationAddsInformation() {
        Continent continent = mock(Continent.class);
        country.addGeographicalInformation(25000000, 7500000, continent);

        assertThat(country).hasFieldOrPropertyWithValue("population", 25000000);
        assertThat(country).hasFieldOrPropertyWithValue("area", 7500000);
        assertThat(country).hasFieldOrPropertyWithValue("continent", continent);
    }

    @Test
    public void addPoliticalInformationAddsInformation() {
        Currency currency = mock(Currency.class);
        HeadOfState headOfState = mock(HeadOfState.class);
        Language language = mock(Language.class);
        City capital = mock(City.class);

        country.addPoliticalInformation(currency,headOfState, language, capital);

        assertThat(country).hasFieldOrPropertyWithValue("currency", currency)
                .hasFieldOrPropertyWithValue("headOfState", headOfState)
                .hasFieldOrPropertyWithValue("language", language)
                .hasFieldOrPropertyWithValue("capital", capital);

    }

    @Test
    public void reelectTheHeadOfStateChangesPresident() throws Exception {

        Voting voting = mock(Voting.class);
        HeadOfState headOfState1 = mock(HeadOfState.class);
        HeadOfState headOfState2 = mock(HeadOfState.class);
        country.addPoliticalInformation(null, headOfState1, null, null);
        country.setVoting(voting);

        when(voting.vote(country)).thenReturn(90);
        country.reelectTheHeadOfState(headOfState2);

        assertThat(country).hasFieldOrPropertyWithValue("headOfState", headOfState2);
    }

    @Test
    public void reelectTheHeadOfStateThrowsException() throws Exception {

        Voting voting = mock(Voting.class);
        HeadOfState headOfState1 = mock(HeadOfState.class);
        HeadOfState headOfState2 = mock(HeadOfState.class);
        country.addPoliticalInformation(null, headOfState1, null, null);
        country.setVoting(voting);

        when(voting.vote(country)).thenReturn(10);
        Assertions.assertThrows(PopulationDidntSupportException.class, () -> country.reelectTheHeadOfState(headOfState2));

    }

    @Test
    public void changeCurrencyChangesCurrency() {
        Currency currency1 = mock(Currency.class);
        Currency currency2 = mock(Currency.class);

        country.addPoliticalInformation(currency1, null, null, null);
        country.changeCurrency(currency2);

        assertThat(country).hasFieldOrPropertyWithValue("currency", currency2);
    }

    @Test
    public void changeOfficialLanguageChangesLanguage() {
        Language language1 = mock(Language.class);
        Language language2 = mock((Language.class));

        country.addPoliticalInformation(null, null, language1, null);
        country.changeOfficialLanguage(language2);

        assertThat(country).hasFieldOrPropertyWithValue("language", language2);
    }

    @Test
    public void moveTheCapitalMovesTheCapitalWhenNewCapitalInTheListOfCities() throws Exception {
        City city  = mock(City.class);
        Voting voting = mock(Voting.class);

        country.addCity(city);
        country.setVoting(voting);
        when(voting.vote(country)).thenReturn(90);
        country.moveTheCapital(city);

        assertThat(country).hasFieldOrPropertyWithValue("capital", city);
    }

    @Test
    public void moveTheCapitalThrowsExceptionWhenCityIsntInTheListOfCities() throws Exception {
        City city  = mock(City.class);
        Voting voting = mock(Voting.class);

        country.setVoting(voting);
        when(voting.vote(country)).thenReturn(10);

        Assertions.assertThrows(PopulationDidntSupportException.class, () -> country.moveTheCapital(city));

    }

    @Test
    public void sellTheCityRemovesCityFromTheListOfCities() throws Exception {
        City city1 = mock(City.class);
        Country country2 = mock((Country.class));

        country.addCity(city1);
        country.sellTheCity(city1, country2);

        assertThat(country).hasFieldOrPropertyWithValue("cities", Arrays.asList());
    }


    @Test
    public void sellTheCityThrowsExceptionWhenCityIsNotInTheList() throws Exception {
        City city1 = mock(City.class);
        Country country2 = mock((Country.class));

        Assertions.assertThrows(CityNotInTheListException.class, () -> country.sellTheCity(city1, country2));

    }

    @Test
    public void getListOfExLeaders() {
    }

    @Test
    public void addCityAddsCityToTheList() {
        City city = mock(City.class);

        country.addCity(city);

        assertThat(country).hasFieldOrPropertyWithValue("cities", Arrays.asList(city));
    }

    @Test
    public void removeCityRemovesCityFromTheList() {
        City city = mock(City.class);

        country.addCity(city);
        country.removeCity(city);

        assertThat(country).hasFieldOrPropertyWithValue("cities", Arrays.asList());

    }

    @Test
    public void addGeoObjectAddsToTheMountainList() {
        Mountain mountain = mock(Mountain.class);

        country.addGeoObject(mountain);

        assertThat(country).hasFieldOrPropertyWithValue("mountains", Arrays.asList(mountain));

    }

    @Test
    public void addGeoObjectAddsToTheOceanList() {
        Ocean ocean = mock(Ocean.class);

        country.addGeoObject(ocean);

        assertThat(country).hasFieldOrPropertyWithValue("oceans", Arrays.asList(ocean));

    }

    @Test
    public void addGeoObjectAddsToTheSeasList() {
        Sea sea = mock(Sea.class);

        country.addGeoObject(sea);

        assertThat(country).hasFieldOrPropertyWithValue("seas", Arrays.asList(sea));

    }

    @Test
    public void removeGeoObjectRemovesFromTheMountainList() {
        Mountain mountain = mock(Mountain.class);

        country.addGeoObject(mountain);
        country.removeGeoObject(mountain);

        assertThat(country).hasFieldOrPropertyWithValue("mountains", Arrays.asList());

    }

    @Test
    public void removeGeoObjectRemovesFromTheOceanList() {
        Ocean ocean = mock(Ocean.class);

        country.addGeoObject(ocean);
        country.removeGeoObject(ocean);

        assertThat(country).hasFieldOrPropertyWithValue("oceans", Arrays.asList());

    }

    @Test
    public void removeGeoObjectRemovesFromTheSeasList() {
        Sea sea = mock(Sea.class);

        country.addGeoObject(sea);
        country.removeGeoObject(sea);

        assertThat(country).hasFieldOrPropertyWithValue("seas", Arrays.asList());

    }

}