package com.eugeneze.converters;

import com.eugeneze.models.*;
import org.springframework.stereotype.Component;

@Component
public class CountryConverter {

    public String convertToJson(Country country) {
        Object[] dataCountry = country.getObjects();
        Object[] dataCurrency = ((Currency)dataCountry[4]).getObjects();
        Object[] dataHeadOfState = ((HeadOfState)dataCountry[5]).getObjects();
        Object[] dataLanguage = ((Language)dataCountry[6]).getObjects();
        Object[] dataContinent = ((Continent)dataCountry[7]).getObjects();
        Object[] dataCapital = ((City)dataCountry[8]).getObjects();

        return "{" + "\n" +
                " \"id\": " + dataCountry[0] + "," + "\n" +
                " \"name\": " + dataCountry[1] + "," + "\n" +
                " \"area\": " + dataCountry[3] + "," + "\n" +
                " \"population\": " + dataCountry[2] + "," + "\n" +
                " \"head of state\": {\n" +
                "  \"id\": " + dataHeadOfState[0] + "," + "\n" +
                "  \"first name\": " + dataHeadOfState[1] + "," + "\n" +
                "  \"last name\": " + dataHeadOfState[2] + "," + "\n" +
                "  \"date of birth\": " + dataHeadOfState[3] + "," + "\n" +
                "  \"title\": " + dataHeadOfState[4] + "\n" + "  }" + "\n" +
                " \"language\": {\n" +
                "  \"id\": " + dataLanguage[0] + "," + "\n" +
                "  \"name\": " + dataLanguage[1] + "," + "\n" +
                "  \"number of native speakers\": " + dataLanguage[2] + "\n" + "  }" + "\n" +
                " \"continent\": {\n" +
                "  \"id\": " + dataContinent[0] + "," + "\n" +
                "  \"name\": " + dataContinent[1] + "," + "\n" +
                "  \"area\": " + dataContinent[2] + "\n" + "  }" + "\n" +
                " \"capital\": {\n" +
                "  \"id\": " + dataCapital[0] + "," + "\n" +
                "  \"name\": " + dataCapital[1] + "," + "\n" +
                "  \"country\": " + dataCountry[1] + "," + "\n" +
                "  \"area\": " + dataCapital[3] + "," + "\n" +
                "  \"population\": " + dataCapital[4] + "\n" + "  }" + "\n" +
                " \"currency\": {\n" +
                "  \"id\": " + dataCurrency[0] + "," + "\n" +
                "  \"name\": " + dataCurrency[1] + "," + "\n" +
                "  \"code\": " + dataCurrency[2] + "\n" + "  }" + "\n" +
                "}";

    }

    public Country convertJsonToObject(String json) {
        Country country = null;
        String[] data = json.split("\".+?\": ");
        country = new Country.Builder(data[2].replaceAll(",\n ", ""))
                .setArea(Integer.parseInt(data[3].substring(0, data[3].length() - 3)))
                .setPopulation(Integer.parseInt(data[4].substring(0, data[4].length() - 3)))
                .setHeadOfState(new HeadOfState.Builder(data[7].replaceAll(",\n  ", ""), data[8].replaceAll(",\n  ", ""))
//                        .setDateOfBirth(data[9].equals("null,\n") ? null : LocalDate.parse(data[9].substring(0, data[17].length() - 3)))
                        .setTitle(data[10].substring(0, data[10].length() - 7)).build())
                .setLanguage(new Language.Builder(data[13].replaceAll(",\n  ", "")).
                        setNumberOfNativeSpeakers(Integer.parseInt(data[14].substring(0, data[14].length() - 6))).build())
                .setContinent(new Continent(Integer.parseInt(data[16].substring(0, data[16].length() - 4)),
                        data[17].replaceAll(",\n  ", ""),
                        Integer.parseInt(data[18].substring(0, data[18].length() - 6))))
//                .setCapital(new City.Builder(data[21].replaceAll(",\n  ", "")).
//                        setArea(Integer.parseInt(data[23].substring(0, data[23].length() - 4))).
//                        setPopulation(Integer.parseInt(data[24].substring(0, data[24].length() - 6))).build())
                .setCurrency(new Currency(data[27].replaceAll(",\n  ", ""),
                        data[28].substring(0, data[28].length() - 6))).build();

        return country;
    }
}
