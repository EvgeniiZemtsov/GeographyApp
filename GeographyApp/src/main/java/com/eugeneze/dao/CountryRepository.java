package com.eugeneze.dao;

import com.eugeneze.models.*;


import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryRepository implements Repository {

    private final ConnectionPool connectionPool;

    public CountryRepository(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<Country> query(Specification<Country> countrySpecification) {

        List<Country> countries = new ArrayList<>();
        try(Connection connection = connectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQLStatements.GET.query + countrySpecification.toSqlClause())) {

            final ResultSet resultSet = preparedStatement.executeQuery();
            Country country = null;

            if (resultSet.next()) {
                country = new Country.Builder(resultSet.getInt("country_id"), resultSet.getString("name"))
                        .setPopulation(resultSet.getInt("population"))
                        .setArea(resultSet.getInt("area"))
                        .setContinent(new Continent(resultSet.getInt("continent_id"),
                                resultSet.getString("continent"),
                                resultSet.getInt("continent_area")))
                        .setCurrency(new Currency(resultSet.getInt("currency_id"),
                                resultSet.getString("currency"),
                                resultSet.getString("currency_code")))
                        .setHeadOfState(new HeadOfState.Builder(resultSet.getInt("head_of_state_id"),
                                resultSet.getString("first_name"),
                                resultSet.getString("last_name")).build())
                        .setLanguage(new Language.Builder(resultSet.getInt("language_id"), resultSet.getString("language")).build())
                        .setCapital(new City.Builder(resultSet.getInt("capital_id"), resultSet.getString("capital")).build()).build();

            }
            if(countrySpecification.isSatisfiedBy(country)) {
                countries.add(country);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return countries;
    }

    @Override
    public void create(Country country) {

        try(Connection connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQLStatements.CREATE.query)) {

            setWildCardsToCreate(country, preparedStatement);

            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void update(Country country) {

        try(Connection connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQLStatements.UPDATE.query)) {

            setWildCardsToUpdate(country, preparedStatement);

            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void delete(String id) {

        try(Connection connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQLStatements.DELETE.query)) {

            preparedStatement.setInt(1, Integer.parseInt(id));
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private void setWildCardsToUpdate(Country country,
                                      PreparedStatement preparedStatement) throws SQLException {
        Object[] dataCountry = country.getObjects();
        Object[] dataCurrency = ((Currency)dataCountry[4]).getObjects();
        Object[] dataHeadOfState = ((HeadOfState)dataCountry[5]).getObjects();
        Object[] dataLanguage = ((Language)dataCountry[6]).getObjects();
        Object[] dataContinent = ((Continent)dataCountry[7]).getObjects();
        Object[] dataCapital = ((City)dataCountry[8]).getObjects();

//        UPDATING country data and IDs
        preparedStatement.setString(1, (String)dataCountry[1]);
        preparedStatement.setInt(2, (int)dataCountry[2]);
        preparedStatement.setInt(3, (int)dataCountry[3]);
        preparedStatement.setInt(4, (int)dataCurrency[0]);
        preparedStatement.setInt(14, (int)dataCurrency[0]);
        preparedStatement.setInt(5, (int)dataHeadOfState[0]);
        preparedStatement.setInt(9, (int)dataHeadOfState[0]);
        preparedStatement.setInt(6, (int)dataLanguage[0]);
        preparedStatement.setInt(17, (int)dataLanguage[0]);
        preparedStatement.setInt(7, (int)dataContinent[0]);
        preparedStatement.setInt(20, (int)dataContinent[0]);
        preparedStatement.setInt(8, (int)dataCountry[0]);

//        UPDATING headOfState data
        preparedStatement.setString(10, (String)dataHeadOfState[1]);
        preparedStatement.setString(11, (String)dataHeadOfState[2]);
        preparedStatement.setDate(12, (Date)dataHeadOfState[3]);
        preparedStatement.setString(13, (String)dataHeadOfState[4]);

//        UPDATING currency data
        preparedStatement.setString(15, (String)dataCurrency[1]);
        preparedStatement.setString(16, (String)dataCurrency[2]);

//        UPDATING language data
        preparedStatement.setString(18, (String)dataLanguage[1]);
        preparedStatement.setInt(19, (int)dataLanguage[2]);

//        UPDATING continent data
        preparedStatement.setString(21, (String)dataContinent[1]);
        preparedStatement.setInt(22, (int)dataContinent[2]);

//        UPDATING city data
        preparedStatement.setInt(23, (int)dataCapital[0]);
        preparedStatement.setString(24, (String)dataCapital[1]);
        preparedStatement.setInt(25, (int)dataCountry[0]);
        preparedStatement.setInt(26, (int)dataCapital[3]);
        preparedStatement.setInt(27, (int)dataCapital[4]);

    }

    private void setWildCardsToCreate(Country country,
                                      PreparedStatement preparedStatement) throws SQLException {

        Object[] dataCountry = country.getObjects();
        Object[] dataCurrency = ((Currency)dataCountry[4]).getObjects();
        Object[] dataHeadOfState = ((HeadOfState)dataCountry[5]).getObjects();
        Object[] dataLanguage = ((Language)dataCountry[6]).getObjects();
        Object[] dataContinent = ((Continent)dataCountry[7]).getObjects();
        Object[] dataCapital = ((City)dataCountry[8]).getObjects();

//        INSERTING country data and IDs
        preparedStatement.setInt(1, (int)dataCountry[0]);
        preparedStatement.setString(2, (String)dataCountry[1]);
        preparedStatement.setInt(3, (int)dataCountry[2]);
        preparedStatement.setInt(4, (int)dataCountry[3]);
        preparedStatement.setInt(5, (int)dataCurrency[0]);
        preparedStatement.setInt(20, (int)dataCurrency[0]);
        preparedStatement.setInt(6, (int)dataHeadOfState[0]);
        preparedStatement.setInt(9, (int)dataHeadOfState[0]);
        preparedStatement.setInt(7, (int)dataLanguage[0]);
        preparedStatement.setInt(14, (int)dataLanguage[0]);
        preparedStatement.setInt(8, (int)dataContinent[0]);
        preparedStatement.setInt(17, (int)dataContinent[0]);

//        INSERTING headOfState data
        preparedStatement.setString(10, (String)dataHeadOfState[1]);
        preparedStatement.setString(11, (String)dataHeadOfState[2]);
        preparedStatement.setDate(12, (Date)dataHeadOfState[3]);
        preparedStatement.setString(13, (String)dataHeadOfState[4]);

//        INSERTING language data
        preparedStatement.setString(15, (String)dataLanguage[1]);
        preparedStatement.setInt(16, (int)dataLanguage[2]);

//        INSERTING continent data
        preparedStatement.setString(18, (String)dataContinent[1]);
        preparedStatement.setInt(19, (int)dataContinent[2]);

//        INSERTING currency data
        preparedStatement.setString(21, (String)dataCurrency[1]);
        preparedStatement.setString(22, (String)dataCurrency[2]);

//        INSERTING city data
        preparedStatement.setInt(23, (int)dataCapital[0]);
        preparedStatement.setString(24, (String)dataCapital[1]);
        preparedStatement.setInt(25, (int)dataCountry[0]);
        preparedStatement.setInt(26, (int)dataCapital[3]);
        preparedStatement.setInt(27, (int)dataCapital[4]);

    }

    enum SQLStatements {
        GET("SELECT country_general.country_id,\n" +
                "       country_general.name AS country,\n" +
                "       country_general.population AS country_pop,\n" +
                "       country_general.area AS country_area,\n" +
                "       cities.city_id AS capital_id,\n" +
                "       cities.name AS capital,\n" +
                "       cities.population AS city_pop,\n" +
                "       cities.area AS city_area,\n" +
                "       country_general.currency_id,\n" +
                "       currency.name AS currency,\n" +
                "       currency.currency_code AS currency_code,\n" +
                "       country_general.head_of_state_id,\n" +
                "       current_heads_of_states.first_name,\n" +
                "       current_heads_of_states.last_name,\n" +
                "       country_general.language_id,\n" +
                "       languages.name AS language,\n" +
                "       country_general.continent_id,\n" +
                "       continents.name AS continent,\n" +
                "       continents.area AS continent_area\n" +
                "\n" +
                "FROM country_general\n" +
                "LEFT JOIN country_capital\n" +
                "ON country_general.country_id = country_capital.country_id\n" +
                "LEFT JOIN cities\n" +
                "ON country_capital.city_id = cities.city_id\n" +
                "LEFT JOIN currency\n" +
                "ON country_general.currency_id = currency.currency_id\n" +
                "LEFT JOIN continents\n" +
                "ON country_general.continent_id = continents.continent_id\n" +
                "LEFT JOIN languages\n" +
                "ON country_general.language_id = languages.language_id\n" +
                "LEFT JOIN current_heads_of_states\n" +
                "ON country_general.head_of_state_id = current_heads_of_states.head_of_state_id" +
                "WHERE "),
        CREATE("INSERT INTO Country_general (country_id, name, " +
                "population, area, currency_id, " +
                "head_of_state_id, language_id, " +
                "continent_id) " +
                "VALUES ((?), (?), (?), (?), (?), (?), (?), (?));" +
                "INSERT into current_heads_of_states" +
                "VALUES ((?), (?), (?), (?), (?))" +
                "on conflict do nothing;" +
                "INSERT INTO Languages" +
                "VALUES ((?), (?), (?))" +
                "on conflict do nothing;" +
                "INSERT INTO Continents" +
                "VALUES ((?), (?), (?))" +
                "on conflict do nothing;" +
                "INSERT INTO Currency" +
                "VALUES ((?), (?), (?))" +
                "on conflict do nothing;" +
                "INSERT INTO Cities" +
                "VALUES ((?), (?), (?), (?), (?))" +
                "on conflict do nothing;"),
        UPDATE("UPDATE Country_general" +
                "SET name = (?), " +
                "population = (?), area = (?), currency_id = (?), " +
                "head_of_state_id = (?), language_id = (?), " +
                "continent_id = (?)" +
                "WHERE country_id = (?);" +
                "INSERT INTO Current_heads_of_states values ((?), (?), (?), (?), (?)) " +
                "on conflict (head_of_state_id) do update SET date_of_birth = Excluded.date_of_birth, " +
                "first_name = Excluded.first_name, last_name = Excluded.last_name, title = Excluded.title;" +
                "INSERT INTO currency values ((?), (?), (?)) " +
                "on conflict (currency_id) do update SET name = Excluded.name, " +
                "currency_code = Excluded.currency_code;" +
                "INSERT INTO languages values ((?), (?), (?)) " +
                "on conflict (language_id) do update SET name = Excluded.name, " +
                "number_of_native_speakers = Excluded.number_of_native_speakers;" +
                "INSERT INTO continents values ((?), (?), (?)) " +
                "on conflict (continent_id) do update SET name = Excluded.name, " +
                "area = Excluded.area;" +
                "INSERT INTO Cities values ((?), (?), (?), (?), (?)) " +
                "on conflict (city_id) do update SET name = Excluded.name, " +
                "country_id = Excluded.country_id, area = Excluded.area, population = Excluded.population;"),
        DELETE("DELETE FROM Country_general" +
                "WHERE id = (?)");

        String query;

        SQLStatements(String query) {
            this.query = query;
        }
    }
}
