package com.eugeneze.springcontrollers;

import com.eugeneze.models.Continent;
import com.eugeneze.service.ContinentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "geoapp/api/continents")
public class ContinentController {
    private final ContinentService service;

    @Autowired
    public ContinentController(ContinentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Continent> getCities() { return service.getContinents(); }

    @GetMapping(path = "/{continentId}")
    public Optional<Continent> getCity(@PathVariable("continentId") Integer continentId) {
        return service.getContinent(continentId);
    }

    @DeleteMapping(path = "/{continentId}")
    public void deleteCity(@PathVariable("continentId") int continentId) {
        service.deleteContinent(continentId);
    }

    @PostMapping
    public ResponseEntity addNewCity(@RequestBody Continent continent) {
        Continent savedContinent = service.addNewContinent(continent);

        HttpHeaders headers = new HttpHeaders();
        headers.add("location", "localhost:8080/geoapp/api/continent/" + savedContinent.getObjects()[0]);

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{continentId}")
    public void updateCountry(
            @PathVariable("continentId") int continentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer area
    ) {
        service.updateContinent(continentId, name, area);
    }
}
