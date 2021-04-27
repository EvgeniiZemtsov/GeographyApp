package com.eugeneze.converters;

import com.eugeneze.models.Country;
import com.eugeneze.models.HeadOfState;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class HeadOfStateConverterTest {

    HeadOfState headOfState = new HeadOfState.Builder(2,
            "Pedro",
            "Sanchez")
            .setDateOfBirth(LocalDate.parse("10-12-1987", DateTimeFormatter.ofPattern("dd-MM-yyyy")))
            .setWorkPlace(new Country.Builder(1, "Spain").setPopulation(12345678).setArea(123456789).build())
            .setTitle("Premier Minister").build();

    String json = "{\n" +
            " \"id\": 2,\n" +
            " \"firstName\": Pedro,\n" +
            " \"lastName\": Sanchez,\n" +
            " \"dateOfBirth\": 1987-12-10,\n" +
            " \"title\": Premier Minister,\n" +
            " \"workplace\": {\n" +
            "  \"id\": 1,\n" +
            "  \"name\": Spain,\n" +
            "  \"area\": 123456789,\n" +
            "  \"population\": 12345678\n" +
            "  }\n" +
            "}";

    HeadOfStateConverter headOfStateConverter = new HeadOfStateConverter();

    @Test
    void convertToJsonConvertsToJson() {
        String result = headOfStateConverter.convertToJson(headOfState);

        assertThat(result).isEqualTo(json);
    }

    @Test
    void convertJsonToObjectConvertsToObject() {
        HeadOfState headOfStateResult = headOfStateConverter.convertJsonToObject(json);

        assertThat(headOfStateResult).isEqualTo(headOfState);
    }
}