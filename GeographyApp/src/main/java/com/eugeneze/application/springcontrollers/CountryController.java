package com.eugeneze.application.springcontrollers;

import com.eugeneze.models.Country;
import com.eugeneze.application.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "geoapp/api/countries")
public class CountryController {
    private final CountryService service;

    @Autowired
    public CountryController(CountryService service) {
        this.service = service;
    }

    @GetMapping
    public List<Country> getCountries() {
        return service.getCountries();
    }

    @GetMapping(path = "/{countryId}")
    public Optional<Country> getCountry(@PathVariable("countryId") int countryId) {
        return service.getCountry(countryId);
    }

    @DeleteMapping(path = "/{countryId}")
    public void deleteCountry(@PathVariable("countryId") int countryId) {
        service.deleteCountry(countryId);
    }

    @PostMapping
    public ResponseEntity addNewCountry(@RequestBody Country country) {
        Country savedCountry = service.addNewCountry(country);

        return ResponseEntity.created(UriComponentsBuilder.fromPath("/geoapp/api/counties/{id}").buildAndExpand(savedCountry.getObjects()[0]).toUri()).build();
    }

    @PutMapping(path = "/{countryId}")
    public void updateCountry(
            @PathVariable("countryId") int countryId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) int population,
            @RequestParam(required = false) int area
    ) {
        service.updateCountry(countryId, name, population, area);
    }
}
