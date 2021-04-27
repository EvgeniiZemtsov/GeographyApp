package com.eugeneze.converters;

import com.eugeneze.models.Language;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LanguageConverterTest {

    Language language = new Language.Builder(2, "Spanish").
            setNumberOfNativeSpeakers(500000000).build();

    String json = "{\n" +
            " \"id\": 2,\n" +
            " \"name\": Spanish,\n" +
            " \"numberOfNativeSpeakers\": 500000000\n" +
            "}";

    LanguageConverter languageConverter = new LanguageConverter();

    @Test
    void convertToJsonConvertsToJson() {
        String result = languageConverter.convertToJson(language);

        assertThat(result).isEqualTo(json);
    }

    @Test
    void convertJsonToObjectConvertsJsonToObject() {
        Language languageResult = languageConverter.convertJsonToObject(json);

        assertThat(languageResult).isEqualTo(language);
    }
}