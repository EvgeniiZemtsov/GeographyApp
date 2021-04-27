package com.eugeneze.converters;

import com.eugeneze.converters.CountryConverter;
import com.eugeneze.models.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class CountryConverterTest {

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
                    "Sanchez").
                    setTitle("Premier Minister").build())
            .setLanguage(new Language.Builder(2, "Spanish").
                    setNumberOfNativeSpeakers(500000000).build())
            .setCapital(new City.Builder(2, "Madrid").
                    setArea(253366).
                    setPopulation(4322332).build()).build();

    String json = "{\n" +
            " \"id\": 2,\n" +
            " \"name\": Spain,\n" +
            " \"area\": 505900,\n" +
            " \"population\": 46940000,\n" +
            " \"head of state\": {\n" +
            "  \"id\": 2,\n" +
            "  \"first name\": Pedro,\n" +
            "  \"last name\": Sanchez,\n" +
            "  \"date of birth\": null,\n" +
            "  \"title\": Premier Minister\n" +
            "  }\n" +
            " \"language\": {\n" +
            "  \"id\": 2,\n" +
            "  \"name\": Spanish,\n" +
            "  \"number of native speakers\": 500000000\n" +
            "  }\n" +
            " \"continent\": {\n" +
            "  \"id\": 1,\n" +
            "  \"name\": Europe,\n" +
            "  \"area\": 22134900\n" +
            "  }\n" +
            " \"capital\": {\n" +
            "  \"id\": 2,\n" +
            "  \"name\": Madrid,\n" +
            "  \"country\": Spain,\n" +
            "  \"area\": 253366,\n" +
            "  \"population\": 4322332\n" +
            "  }\n" +
            " \"currency\": {\n" +
            "  \"id\": 1,\n" +
            "  \"name\": Euro,\n" +
            "  \"code\": EUR\n" +
            "  }\n" +
            "}";

    CountryConverter countryConverter = new CountryConverter();

    @Test
    void convertToJsonConvertsToJson() {
        String result = countryConverter.convertToJson(country);

        assertThat(result).isEqualTo(json);

    }

    @Test
    void convertJsonToObjectConvertsJsonToObject() {
        Country countryResult = countryConverter.convertJsonToObject(json);

        assertThat(countryResult).isEqualTo(country);
    }
}