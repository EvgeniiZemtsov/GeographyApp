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
                country = new Country(resultSet.getInt("country_id"), resultSet.getString("name"));
                country.addGeographicalInformation(resultSet.getInt("population"), resultSet.getInt("area"),
                        new Continent(resultSet.getInt("continent_id"),
                                resultSet.getString("continent"),
                                resultSet.getInt("continent_area")));

                country.addPoliticalInformation(
                        new Currency(resultSet.getInt("currency_id"), resultSet.getString("currency"), resultSet.getString("currency_code")),
                        new HeadOfState(resultSet.getInt("head_of_state_id"), resultSet.getString("first_name"), resultSet.getString("last_name")),
                        new Language(resultSet.getInt("language_id"), resultSet.getString("language")),
                        new City(resultSet.getInt("capital_id"), resultSet.getString("capital")));
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

        } catch (SQLException | IllegalAccessException | NoSuchFieldException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void update(Country country) {

        try(Connection connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQLStatements.UPDATE.query)) {

            setWildCardsToUpdate(country, preparedStatement);

            preparedStatement.executeUpdate();

        } catch (SQLException | NoSuchFieldException | IllegalAccessException throwables) {
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
                                      PreparedStatement preparedStatement) throws NoSuchFieldException, IllegalAccessException, SQLException {
//          update field "name" in database
        Field nameField = country.getClass().getDeclaredField("name");
        nameField.setAccessible(true);
        String name = (String) nameField.get(country);

        preparedStatement.setString(1, name);

//          update field "population" in database
        Field populationField = country.getClass().getDeclaredField("population");
        populationField.setAccessible(true);
        int population = populationField.getInt(country);

        preparedStatement.setInt(2, population);

//          update field "area" in database
        Field areaField = country.getClass().getDeclaredField("area");
        areaField.setAccessible(true);
        int area = areaField.getInt(country);

        preparedStatement.setInt(3, area);

//          update field "currency_id" in database
        Field currencyField = country.getClass().getDeclaredField("currency");
        currencyField.setAccessible(true);
        Currency currency = (Currency) currencyField.get(country);

        Field currencyIdField = currency.getClass().getDeclaredField("id");
        currencyIdField.setAccessible(true);
        int currencyId = currencyIdField.getInt(currency);

        preparedStatement.setInt(4, currencyId);
        preparedStatement.setInt(14, currencyId);

//          update field "head_of_state_id" in database
        Field headOfStateField = country.getClass().getDeclaredField("headOfState");
        headOfStateField.setAccessible(true);
        HeadOfState headOfState = (HeadOfState) headOfStateField.get(country);

        Field headOfStateIdField = headOfState.getClass().getDeclaredField("id");
        headOfStateIdField.setAccessible(true);
        int headOfStateId = currencyIdField.getInt(currency);

        preparedStatement.setInt(5, headOfStateId);
        preparedStatement.setInt(9, headOfStateId);

//          update field "language_id" in database
        Field languageField = country.getClass().getDeclaredField("language");
        languageField.setAccessible(true);
        Language language = (Language) languageField.get(country);

        Field languageIdField = language.getClass().getDeclaredField("id");
        languageIdField.setAccessible(true);
        int languageId = languageIdField.getInt(currency);

        preparedStatement.setInt(6, languageId);
        preparedStatement.setInt(17, languageId);

//          update field "continent_id" in database
        Field continentField = country.getClass().getDeclaredField("continent");
        continentField.setAccessible(true);
        Continent continent = (Continent) continentField.get(country);

        Field continentIdField = continent.getClass().getDeclaredField("id");
        continentIdField.setAccessible(true);
        int continentId = continentIdField.getInt(currency);

        preparedStatement.setInt(7, continentId);
        preparedStatement.setInt(20, continentId);

//          update field "id" in database
        Field countryIdField = country.getClass().getDeclaredField("id");
        countryIdField.setAccessible(true);
        int id = countryIdField.getInt(country);

        preparedStatement.setInt(8, id);

//        updating current_head_of_state record
        Field firstNameField = headOfState.getClass().getDeclaredField("firstName");
        Field lastNameField = headOfState.getClass().getDeclaredField("lastName");
        Field dateOfBirthField = headOfState.getClass().getDeclaredField("dateOfBirth");
        Field titleField = headOfState.getClass().getDeclaredField("title");

        firstNameField.setAccessible(true);
        lastNameField.setAccessible(true);
        dateOfBirthField.setAccessible(true);
        titleField.setAccessible(true);

        String firstName = (String)firstNameField.get(headOfState);
        String lastName = (String)lastNameField.get(headOfState);
        Date dateOfBirth = (Date)dateOfBirthField.get(headOfState);
        String title = (String)titleField.get(headOfState);

        preparedStatement.setString(10, firstName);
        preparedStatement.setString(11, lastName);
        preparedStatement.setDate(12, dateOfBirth);
        preparedStatement.setString(13, title);

//        updating currency record
        Field currencyNameField = currency.getClass().getDeclaredField("name");
        Field currencyCodeField = currency.getClass().getDeclaredField("code");

        currencyNameField.setAccessible(true);
        currencyCodeField.setAccessible(true);

        String currencyName = (String)currencyNameField.get(currency);
        String currencyCode = (String)currencyCodeField.get(currency);

        preparedStatement.setString(15, currencyName);
        preparedStatement.setString(16, currencyCode);

//        updating language record
        Field languageNameField = language.getClass().getDeclaredField("name");
        Field numberOfNativeSpeakersField = language.getClass().getDeclaredField("numberOfNativeSpeakers");

        languageNameField.setAccessible(true);
        numberOfNativeSpeakersField.setAccessible(true);

        String languageName = (String)languageNameField.get(language);
        int numberOfNativeSpeakers = numberOfNativeSpeakersField.getInt(language);

        preparedStatement.setString(18, languageName);
        preparedStatement.setInt(19, numberOfNativeSpeakers);

//        updating continent record
        Field continentNameField = continent.getClass().getDeclaredField("name");
        Field continentAreaField = continent.getClass().getDeclaredField("area");

        continentNameField.setAccessible(true);
        continentAreaField.setAccessible(true);

        String continentName = (String)continentNameField.get(continent);
        int continentArea = continentAreaField.getInt(continent);

        preparedStatement.setString(21, continentName);
        preparedStatement.setInt(22, continentArea);
    }

    private void setWildCardsToCreate(Country country,
                                      PreparedStatement preparedStatement) throws NoSuchFieldException, IllegalAccessException, SQLException {

//          adding field "id" to database
        Field countryIdField = country.getClass().getDeclaredField("id");
        countryIdField.setAccessible(true);
        int id = countryIdField.getInt(country);

        preparedStatement.setInt(1, id);

//          adding field "name" to database
        Field nameField = country.getClass().getDeclaredField("name");
        nameField.setAccessible(true);
        String name = (String) nameField.get(country);

        preparedStatement.setString(2, name);

//          adding field "population" to database
        Field populationField = country.getClass().getDeclaredField("population");
        populationField.setAccessible(true);
        int population = populationField.getInt(country);

        preparedStatement.setInt(3, population);

//          adding field "area" to database
        Field areaField = country.getClass().getDeclaredField("area");
        areaField.setAccessible(true);
        int area = areaField.getInt(country);

        preparedStatement.setInt(4, area);

//          adding field "currency_id" to database
        Field currencyField = country.getClass().getDeclaredField("currency");
        currencyField.setAccessible(true);
        Currency currency = (Currency) currencyField.get(country);

        Field currencyIdField = currency.getClass().getDeclaredField("id");
        currencyIdField.setAccessible(true);
        int currencyId = currencyIdField.getInt(currency);

        preparedStatement.setInt(5, currencyId);

//          adding field "head_of_state_id" to database
        Field headOfStateField = country.getClass().getDeclaredField("headOfState");
        headOfStateField.setAccessible(true);
        HeadOfState headOfState = (HeadOfState) headOfStateField.get(country);

        Field headOfStateIdField = headOfState.getClass().getDeclaredField("id");
        headOfStateIdField.setAccessible(true);
        int headOfStateId = headOfStateIdField.getInt(currency);

        preparedStatement.setInt(6, headOfStateId);


//          adding field "language_id" to database
        Field languageField = country.getClass().getDeclaredField("language");
        languageField.setAccessible(true);
        Language language = (Language) languageField.get(country);

        Field languageIdField = language.getClass().getDeclaredField("id");
        languageIdField.setAccessible(true);
        int languageId = languageIdField.getInt(currency);

        preparedStatement.setInt(7, languageId);

//          adding field "continent_id" to database
        Field continentField = country.getClass().getDeclaredField("continent");
        continentField.setAccessible(true);
        Continent continent = (Continent) continentField.get(country);

        Field continentIdField = continent.getClass().getDeclaredField("id");
        continentIdField.setAccessible(true);
        int continentId = continentIdField.getInt(currency);

        preparedStatement.setInt(8, continentId);

//        adding head_of_state to database
        preparedStatement.setInt(9, headOfStateId);

        Field firstNameField = headOfState.getClass().getDeclaredField("firstName");
        firstNameField.setAccessible(true);
        String firstName = (String)firstNameField.get(headOfState);
        preparedStatement.setString(10, firstName);

        Field lastNameField = headOfState.getClass().getDeclaredField("lastName");
        lastNameField.setAccessible(true);
        String lastName = (String)lastNameField.get(headOfState);
        preparedStatement.setString(11, lastName);

        Field dateOfBirthField = headOfState.getClass().getDeclaredField("dateOfBirth");
        dateOfBirthField.setAccessible(true);
        Date dateOfBirth = (Date)dateOfBirthField.get(headOfState);
        preparedStatement.setDate(12, dateOfBirth);

        Field titleField = headOfState.getClass().getDeclaredField("title");
        titleField.setAccessible(true);
        String title = (String)titleField.get(headOfState);
        preparedStatement.setString(13, title);
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
                "on conflict do nothing"),
        UPDATE("UPDATE Country_general" +
                "SET name = (?), " +
                "population = (?), area = (?), currency_id = (?), " +
                "head_of_state_id = (?), language_id = (?), " +
                "continent_id = (?)" +
                "WHERE country_id = (?);" +
                "INSERT INTO Current_heads_of_states values ((?), (?), (?), (?), (?)) " +
                "on conflict (head_of_state_id) do update SET date_of_birth = Excluded.date_of_birth, " +
                "first_name = Excluded.first_name, last_name = Excluded.last_name, title = Excluded. title;" +
                "INSERT INTO currency values ((?), (?), (?)) " +
                "on conflict (currency_id) do update SET name = Excluded.name, " +
                "currency_code = Excluded.currency_code;" +
                "INSERT INTO languages values ((?), (?), (?)) " +
                "on conflict (language_id) do update SET name = Excluded.name, " +
                "number_of_native_speakers = Excluded.number_of_native_speakers;" +
                "INSERT INTO continents values ((?), (?), (?)) " +
                "on conflict (continent_id) do update SET name = Excluded.name, " +
                "area = Excluded.area"),
        DELETE("DELETE FROM Country_general" +
                "WHERE id = (?)");

        String query;

        SQLStatements(String query) {
            this.query = query;
        }
    }
}
