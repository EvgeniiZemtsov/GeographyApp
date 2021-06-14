package com.eugeneze.springcontrollers;

import com.eugeneze.models.City;
import com.eugeneze.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "geoapp/api/cities")
public class CityController {
    private final CityService service;

    @Autowired
    public CityController(CityService service) {
        this.service = service;
    }

    @GetMapping
    public List<City> getCities() { return service.getCities(); }

    @GetMapping(path = "/{cityId}")
    public Optional<City> getCity(@PathVariable("cityId") int cityId) {
        return service.getCity(cityId);
    }

    @DeleteMapping(path = "/{cityId}")
    public void deleteCity(@PathVariable("cityId") int cityId) {
        service.deleteCity(cityId);
    }

    @PostMapping
    public ResponseEntity addNewCity(@RequestBody City city) {
        City savedCity = service.addNewCity(city);

        HttpHeaders headers = new HttpHeaders();
        headers.add("location", "localhost:8080/geoapp/api/cities/" + savedCity.getObjects()[0]);

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{cityId}")
    public void updateCountry(
            @PathVariable("cityId") int cityId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) int population,
            @RequestParam(required = false) int area
    ) {
        service.updateCity(cityId, name, population, area);
    }
}
