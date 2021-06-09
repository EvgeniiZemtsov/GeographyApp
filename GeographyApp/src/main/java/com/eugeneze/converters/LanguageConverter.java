package com.eugeneze.converters;

import com.eugeneze.models.Language;
import org.springframework.stereotype.Component;

@Component
public class LanguageConverter {
    public String convertToJson(Language language) {
        Object[] dataLanguage = language.getObjects();

        return "{" + "\n" +
                " \"id\": " + dataLanguage[0] + "," + "\n" +
                " \"name\": " + dataLanguage[1] + "," + "\n" +
                " \"numberOfNativeSpeakers\": " + dataLanguage[2] + "\n" +
                "}";

    }

    public Language convertJsonToObject(String json) {
        Language language = null;

        String[] data = json.split("\".+?\": ");
        language = new Language.Builder(data[2].replaceAll(",\n ", ""))
                .setNumberOfNativeSpeakers(Integer.parseInt(data[3].substring(0, data[3].length() - 2))).build();

        return language;
    }
}
