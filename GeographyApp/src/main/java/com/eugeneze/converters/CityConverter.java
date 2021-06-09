package com.eugeneze.converters;

import com.eugeneze.models.*;
import org.springframework.stereotype.Component;

@Component
public class CityConverter {

    public String convertToJson(City city) {

        Object[] dataCity = city.getObjects();
        Object[] dataCountry = null;
        if(dataCity[2] != null)
         dataCountry = ((Country)dataCity[2]).getObjects();
        String countryName = (dataCity[2] == null ? "null" : (String)dataCountry[1]);

        return "{" + "\n" +
                " \"id\": " + dataCity[0] + "," + "\n" +
                " \"name\": " + dataCity[1] + "," + "\n" +
                " \"area\": " + dataCity[3] + "," + "\n" +
                " \"population\": " + dataCity[4] + "," + "\n" +
                " \"country\": {\n" +
                "  \"id\": " + dataCountry[0] + "," + "\n" +
                "  \"name\": " + dataCountry[1] + "," + "\n" +
                "  \"area\": " + dataCountry[3] + "," + "\n" +
                "  \"population\": " + dataCountry[2] + "\n" + "  }" + "\n" +
                "}";

    }

    public City convertJsonToObject(String json) {
        City city = null;
        String[] data = json.split("\".+?\": ");
        city = new City.Builder(data[2].replaceAll(",\n ", ""))
                .setArea(Integer.parseInt(data[3].substring(0, data[3].length() - 3)))
                .setPopulation(Integer.parseInt(data[4].substring(0, data[4].length() - 3)))
                .setCountry(new Country.Builder(data[7].replaceAll(",\n  ", ""))
                        .setArea(Integer.parseInt(data[8].substring(0, data[8].length() - 4)))
                        .setPopulation(Integer.parseInt(data[9].substring(0, data[9].length() - 6))).build()).build();
        return city;
    }
}
