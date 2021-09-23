package com.eugeneze.study.converters;

import com.eugeneze.models.Country;
import com.eugeneze.models.HeadOfState;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class HeadOfStateConverter {

    public String convertToJson(HeadOfState headOfState) {
        Object[] dataHeadOfState = headOfState.getObjects();
        Object[] dataCountry = null;
        if(dataHeadOfState[5] != null)
            dataCountry = ((Country)dataHeadOfState[5]).getObjects();
        String countryName = (dataHeadOfState[5] == null ? "null" : (String)dataCountry[1]);

        return "{" + "\n" +
                " \"id\": " + dataHeadOfState[0] + "," + "\n" +
                " \"firstName\": " + dataHeadOfState[1] + "," + "\n" +
                " \"lastName\": " + dataHeadOfState[2] + "," + "\n" +
                " \"dateOfBirth\": " + dataHeadOfState[3] + "," + "\n" +
                " \"title\": " + dataHeadOfState[4] + "," + "\n" +
                " \"workplace\": {\n" +
                "  \"id\": " + dataCountry[0] + "," + "\n" +
                "  \"name\": " + dataCountry[1] + "," + "\n" +
                "  \"area\": " + dataCountry[3] + "," + "\n" +
                "  \"population\": " + dataCountry[2] + "\n" + "  }" + "\n" +
                "}";

    }

    public HeadOfState convertJsonToObject(String json) {
        HeadOfState headOfState = null;
        String[] data = json.split("\".+?\": ");
        headOfState = new HeadOfState.Builder(data[2].replaceAll(",\n ", ""), data[3].replaceAll(",\n ", ""))
                .setTitle(data[5].substring(0, data[5].length() - 3))
                .setDateOfBirth(LocalDate.parse(data[4].substring(0, data[4].length() - 3)))
                .setWorkPlace(new Country.Builder(data[8].replaceAll(",\n  ", ""))
                        .setArea(Integer.parseInt(data[9].substring(0, data[9].length() - 4)))
                        .setPopulation(Integer.parseInt(data[10].substring(0, data[10].length() - 6))).build()).build();

        return headOfState;
    }
}
