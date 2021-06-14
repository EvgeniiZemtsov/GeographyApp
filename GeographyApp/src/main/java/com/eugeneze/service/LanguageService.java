package com.eugeneze.service;

import com.eugeneze.models.Language;
import com.eugeneze.springrepositories.SpringLanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LanguageService {
    private final SpringLanguageRepository repository;

    @Autowired
    public LanguageService(SpringLanguageRepository repository) {
        this.repository = repository;
    }

    public List<Language> getLanguages() {
        return repository.findAll();
    }

    public Optional<Language> getLanguage(int languageId) {
        boolean exists = repository.existsById(languageId);

        if (!exists) {
            throw new IllegalStateException("Continent with id = " + languageId + " doesn't exist");
        }

        return repository.findById(languageId);
    }

    public Language addNewLanguage(Language language) {
        Optional<Language> languageOptional = repository.findLanguageByName((String) language.getObjects()[1]);

        if (languageOptional.isPresent()) {
            throw new IllegalStateException("Language already exists");
        }

        return repository.save(language);
    }

    public void deleteLanguage(int languageId) {
        boolean exists = repository.existsById(languageId);

        if (!exists) {
            throw new IllegalStateException("Language with id = " + languageId + " doesn't exist");
        }

        repository.deleteById(languageId);
    }

    @Transactional
    public void updateLanguage(int languageId, String name, int numberOfNativeSpeakers) {
        Language language = repository.findById(languageId).orElseThrow(() -> new IllegalStateException("Language with id = " + languageId + " doesn't exist"));

        if (name != null && name.length() > 0 && !Objects.equals(language.getObjects()[1], name)) {
            language.setName(name);
        }

        if (numberOfNativeSpeakers != 0 && (Integer)language.getObjects()[2] != numberOfNativeSpeakers) {
            language.setNumberOfNativeSpeakers(numberOfNativeSpeakers);
        }
    }
}
