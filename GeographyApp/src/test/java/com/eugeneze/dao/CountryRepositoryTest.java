package com.eugeneze.dao;

import com.eugeneze.models.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CountryRepositoryTest extends AbstractContainerDatabaseTest {

    private PostgreSQLContainer<?> container;
    private CountryRepository countryRepository;

    @BeforeEach
    void setUp() {
        container = new PostgreSQLContainer<>("postgres")
                .withUsername("evgenii9668")
                .withPassword("lamborgini235")
                .withInitScript("init-db.sql");
        container.start();
        countryRepository = new CountryRepository(new DBPool(container.getUsername(), container.getJdbcUrl(), container.getPassword()));
    }

    @Test
    void queryGetByNameReturnsCountry() throws SQLException {
        List<Country> countriesFromDataBase = countryRepository.query(new FindByNameSpecification("France"));
        Country country = countriesFromDataBase.get(0);

        assertThat(country).hasFieldOrPropertyWithValue("name", "France");

    }

    @Test
    void queryGetByIdReturnsCountry() throws SQLException {
        List<Country> countriesFromDataBase = countryRepository.query(new FindByNameSpecification("1"));
        Country country = countriesFromDataBase.get(0);

        assertThat(country).hasFieldOrPropertyWithValue("id", "1");
    }

    @Test
    void createCreatesRecordsInMainTable() throws SQLException {
        Country country = new Country.Builder(2, "Spain")
                .setPopulation(46940000)
                .setArea(505900)
                .setContinent(new Continent(1,
                        "Europe",
                        22134900))
                .setCurrency(new Currency(1,
                        "Euro",
                        "EUR"))
                .setHeadOfState(new HeadOfState.Builder(2,
                        "Pedro",
                        "Sanchez").build())
                .setLanguage(new Language.Builder(2, "Spanish").build())
                .setCapital(new City.Builder(2, "Madrid").build()).build();

        countryRepository.create(country);

        ResultSet resultSet = performQuery(container, "SELECT* FROM Country_general WHERE country_id = 2");

        assertThat(resultSet.getString(2)).isEqualTo("Spain");
        assertThat(resultSet.getInt(3)).isEqualTo(46940000);
        assertThat(resultSet.getInt(4)).isEqualTo(505900);
        assertThat(resultSet.getInt(5)).isEqualTo(1);
        assertThat(resultSet.getInt(6)).isEqualTo(2);
        assertThat(resultSet.getInt(7)).isEqualTo(2);
        assertThat(resultSet.getInt(8)).isEqualTo(1);
    }

    @Test
    void createCreatesRecordsInHeadsOfStatesTable() throws SQLException {
        Country country = new Country.Builder(2, "Spain")
                .setPopulation(46940000)
                .setArea(505900)
                .setContinent(new Continent(1,
                        "Europe",
                        22134900))
                .setCurrency(new Currency(1,
                        "Euro",
                        "EUR"))
                .setHeadOfState(new HeadOfState.Builder(2,
                        "Pedro",
                        "Sanchez").build())
                .setLanguage(new Language.Builder(2, "Spanish").build())
                .setCapital(new City.Builder(2, "Madrid").build()).build();

        countryRepository.create(country);

        ResultSet resultSet = performQuery(container, "SELECT* FROM Current_heads_of_states WHERE head_of_state_id = 2");

        assertThat(resultSet.getString(2)).isEqualTo("Pedro");
        assertThat(resultSet.getString(2)).isEqualTo("Sanchez");

    }

    @Test
    void createCreatesRecordsInLanguagesTable() throws SQLException {
        Country country = new Country.Builder(2, "Spain")
                .setPopulation(46940000)
                .setArea(505900)
                .setContinent(new Continent(1,
                        "Europe",
                        22134900))
                .setCurrency(new Currency(1,
                        "Euro",
                        "EUR"))
                .setHeadOfState(new HeadOfState.Builder(2,
                        "Pedro",
                        "Sanchez").build())
                .setLanguage(new Language.Builder(2, "Spanish").build())
                .setCapital(new City.Builder(2, "Madrid").build()).build();

        countryRepository.create(country);

        ResultSet resultSet = performQuery(container, "SELECT* FROM Languages WHERE language_id = 2");

        assertThat(resultSet.getString(2)).isEqualTo("Spanish");

    }

    @Test
    void createCreatesRecordsInCitiesTable() throws SQLException {
        Country country = new Country.Builder(2, "Spain")
                .setPopulation(46940000)
                .setArea(505900)
                .setContinent(new Continent(1,
                        "Europe",
                        22134900))
                .setCurrency(new Currency(1,
                        "Euro",
                        "EUR"))
                .setHeadOfState(new HeadOfState.Builder(2,
                        "Pedro",
                        "Sanchez").build())
                .setLanguage(new Language.Builder(2, "Spanish").build())
                .setCapital(new City.Builder(2, "Madrid").build()).build();

        countryRepository.create(country);

        ResultSet resultSet = performQuery(container, "SELECT* FROM Cities WHERE city_id = 2");

        assertThat(resultSet.getString(2)).isEqualTo("Madrid");

    }

    @Test
    void updateUpdatesCountryRecord() throws SQLException {
        Country country = new Country.Builder(1, "Spain")
                .setPopulation(46940000)
                .setArea(505900)
                .setContinent(new Continent(1,
                        "Europe",
                        22134900))
                .setCurrency(new Currency(1,
                        "Euro",
                        "EUR"))
                .setHeadOfState(new HeadOfState.Builder(2,
                        "Pedro",
                        "Sanchez").build())
                .setLanguage(new Language.Builder(2, "Spanish").build())
                .setCapital(new City.Builder(2, "Madrid").build()).build();

        countryRepository.update(country);

        ResultSet resultSet = performQuery(container, "SELECT* FROM Country_general WHERE country_id = 1");

        assertThat(resultSet.getString(2)).isEqualTo("Spain");
        assertThat(resultSet.getInt(3)).isEqualTo(46940000);
        assertThat(resultSet.getInt(4)).isEqualTo(505900);
        assertThat(resultSet.getInt(5)).isEqualTo(1);
        assertThat(resultSet.getInt(6)).isEqualTo(2);
        assertThat(resultSet.getInt(7)).isEqualTo(2);
        assertThat(resultSet.getInt(8)).isEqualTo(1);
    }

    @Test
    void updateUpdatesLanguageRecord() throws SQLException {
        Country country = new Country.Builder(1, "Spain")
                .setPopulation(46940000)
                .setArea(505900)
                .setContinent(new Continent(1,
                        "Europe",
                        22134900))
                .setCurrency(new Currency(1,
                        "Euro",
                        "EUR"))
                .setHeadOfState(new HeadOfState.Builder(2,
                        "Pedro",
                        "Sanchez").build())
                .setLanguage(new Language.Builder(2, "Spanish").build())
                .setCapital(new City.Builder(2, "Madrid").build()).build();

        countryRepository.update(country);

        ResultSet resultSet = performQuery(container, "SELECT* FROM Languages WHERE language_id = 2");

        assertThat(resultSet.getString(2)).isEqualTo("Spanish");
    }

    @Test
    void updateUpdatesHeadOfStateRecord() throws SQLException {
        Country country = new Country.Builder(1, "Spain")
                .setPopulation(46940000)
                .setArea(505900)
                .setContinent(new Continent(1,
                        "Europe",
                        22134900))
                .setCurrency(new Currency(1,
                        "Euro",
                        "EUR"))
                .setHeadOfState(new HeadOfState.Builder(2,
                        "Pedro",
                        "Sanchez").build())
                .setLanguage(new Language.Builder(2, "Spanish").build())
                .setCapital(new City.Builder(2, "Madrid").build()).build();

        countryRepository.update(country);

        ResultSet resultSet = performQuery(container, "SELECT* FROM Current_heads_of_states WHERE head_of_state_id = 1");

        assertThat(resultSet.getString(2)).isEqualTo("Pedro");
        assertThat(resultSet.getString(2)).isEqualTo("Sanchez");

    }

    @Test
    void updateUpdatesCityRecord() throws SQLException {
        Country country = new Country.Builder(1, "Spain")
                .setPopulation(46940000)
                .setArea(505900)
                .setContinent(new Continent(1,
                        "Europe",
                        22134900))
                .setCurrency(new Currency(1,
                        "Euro",
                        "EUR"))
                .setHeadOfState(new HeadOfState.Builder(2,
                        "Pedro",
                        "Sanchez").build())
                .setLanguage(new Language.Builder(2, "Spanish").build())
                .setCapital(new City.Builder(2, "Madrid").build()).build();

        countryRepository.update(country);

        ResultSet resultSet = performQuery(container, "SELECT* FROM Cities WHERE city_id = 1");

        assertThat(resultSet.getString(2)).isEqualTo("Madrid");

    }

    @Test
    void deleteDeletesTheRecord() throws SQLException {
        countryRepository.delete("1");

        ResultSet resultSet = performQuery(container, "SELECT* FROM Country_general");
        int size = resultSet.getFetchSize();

        assertThat(size).isEqualTo(0);

    }

    @AfterEach
    void closeContainer() {
        container.close();
    }

}