package com.eugeneze.springcontrollers;

import com.eugeneze.models.Sight;
import com.eugeneze.service.SightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "geoapp/api/sights")
public class SightController {
    private final SightService service;

    @Autowired
    public SightController(SightService service) {
        this.service = service;
    }

    @GetMapping
    public List<Sight> getSights() { return service.getSights(); }

    @GetMapping(path = "/{sightId}")
    public Optional<Sight> getSea(@PathVariable("sightId") int sightId) {
        return service.getSight(sightId);
    }

    @DeleteMapping(path = "/{sightId}")
    public void deleteSight(@PathVariable("sightId") int sightId) {
        service.deleteSight(sightId);
    }

    @PostMapping
    public ResponseEntity addNewSight(@RequestBody Sight sight) {
        Sight savedSight = service.addNewSight(sight);

        HttpHeaders headers = new HttpHeaders();
        headers.add("location", "localhost:8080/geoapp/api/sights/" + savedSight.getObjects()[0]);

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{sightId}")
    public void updateSight(
            @PathVariable("sightId") int sightId,
            @RequestParam(required = false) String name
    ) {
        service.updateSight(sightId, name);
    }
}
