package com.eugeneze.converters;

import com.eugeneze.models.Currency;

public class CurrencyConverter {
    public String convertToJson(Currency currency) {
        Object[] dataCurrency = currency.getObjects();

        return "{" + "\n" +
                " \"id\": " + dataCurrency[0] + "," + "\n" +
                " \"name\": " + dataCurrency[1] + "," + "\n" +
                " \"code\": " + dataCurrency[2] + "\n" +
                "}";

    }

    public Currency convertJsonToObject(String json) {
        Currency currency = null;
        String[] data = json.split("\".+?\": ");
        currency = new Currency(Integer.parseInt(data[1].substring(0, data[1].length() - 3)),
                data[2].replaceAll(",\n ", ""),
                data[3].replaceAll("\n}", ""));

        return currency;
    }
}
