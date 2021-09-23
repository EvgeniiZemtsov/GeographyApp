package com.eugeneze.application.springcontrollers;

import com.eugeneze.models.Sea;
import com.eugeneze.application.service.SeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "geoapp/api/seas")
public class SeaController {
    private final SeaService service;

    @Autowired
    public SeaController(SeaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Sea> getSeas() { return service.getSeas(); }

    @GetMapping(path = "/{seaId}")
    public Optional<Sea> getSea(@PathVariable("seaId") int seaId) {
        return service.getSea(seaId);
    }

    @DeleteMapping(path = "/{seaId}")
    public void deleteSea(@PathVariable("seaId") int seaId) {
        service.deleteSea(seaId);
    }

    @PostMapping
    public ResponseEntity addNewSea(@RequestBody Sea sea) {
        Sea savedSea = service.addNewSea(sea);

        return ResponseEntity.created(UriComponentsBuilder.fromPath("/geoapp/api/seas/{id}").buildAndExpand(savedSea.getObjects()[0]).toUri()).build();
    }

    @PutMapping(path = "/{seaId}")
    public void updateSea(
            @PathVariable("seaId") int seaId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) int maxDepth,
            @RequestParam(required = false) int area
    ) {
        service.updateSea(seaId, name, maxDepth, area);
    }
}
