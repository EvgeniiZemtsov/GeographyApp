package com.eugeneze.springcontrollers;

import com.eugeneze.models.Language;
import com.eugeneze.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "geoapp/api/languages")
public class LanguageController {
    private final LanguageService service;

    @Autowired
    public LanguageController(LanguageService service) {
        this.service = service;
    }

    @GetMapping
    public List<Language> getLanguages() { return service.getLanguages(); }

    @GetMapping(path = "/{languageId}")
    public Optional<Language> getCity(@PathVariable("languageId") int languageId) {
        return service.getLanguage(languageId);
    }

    @DeleteMapping(path = "/{languageId}")
    public void deleteCity(@PathVariable("languageId") int languageId) {
        service.deleteLanguage(languageId);
    }

    @PostMapping
    public ResponseEntity addNewLanguage(@RequestBody Language language) {
        Language savedLanguage = service.addNewLanguage(language);

        return ResponseEntity.created(UriComponentsBuilder.fromPath("/geoapp/api/languages/{id}").buildAndExpand(savedLanguage.getObjects()[0]).toUri()).build();
    }

    @PutMapping(path = "/{languageId}")
    public void updateLanguage(
            @PathVariable("languageId") int languageId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) int numberOfNativeSpeakers
    ) {
        service.updateLanguage(languageId, name, numberOfNativeSpeakers);
    }
}
