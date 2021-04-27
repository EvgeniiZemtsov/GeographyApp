package com.eugeneze.converters;

import com.eugeneze.models.City;
import com.eugeneze.models.Country;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CityConverterTest {

    City city = new City.Builder(2, "Madrid")
            .setArea(253366)
            .setCountry(new Country.Builder(1, "Spain").setArea(4343).setPopulation(76544).build())
            .setPopulation(4322332).build();

    String json = "{\n" +
            " \"id\": 2,\n" +
            " \"name\": Madrid,\n" +
            " \"area\": 253366,\n" +
            " \"population\": 4322332,\n" +
            " \"country\": {\n" +
            "  \"id\": 1,\n" +
            "  \"name\": Spain,\n" +
            "  \"area\": 4343,\n" +
            "  \"population\": 76544\n" +
            "  }\n" +
            "}";

    CityConverter cityConverter = new CityConverter();

    @Test
    void convertToJsonConvertsToJson() {
        String result = cityConverter.convertToJson(city);

        assertThat(result).isEqualTo(json);
    }

    @Test
    void convertJsonToObject() {
        City cityResult = cityConverter.convertJsonToObject(json);

        assertThat(cityResult).isEqualTo(city);
    }
}