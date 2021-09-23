package com.eugeneze.converters;

import com.eugeneze.models.Currency;
import com.eugeneze.study.converters.CurrencyConverter;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CurrencyConverterTest {

    Currency currency = new Currency(1,
            "Euro",
            "EUR");

    String json = "{\n" +
            " \"id\": 1,\n" +
            " \"name\": Euro,\n" +
            " \"code\": EUR\n" +
            "}";

    CurrencyConverter currencyConverter = new CurrencyConverter();

    @Test
    void convertToJsonConvertsToJson() {
        String result = currencyConverter.convertToJson(currency);

        assertThat(result).isEqualTo(json);
    }

    @Test
    void convertJsonToObjectConvertsJsonToObject() {
        Currency currencyResult = currencyConverter.convertJsonToObject(json);

        assertThat(currencyResult).isEqualTo(currency);
    }
}