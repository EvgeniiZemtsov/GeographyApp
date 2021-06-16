package com.eugeneze.springcontrollers;

import com.eugeneze.models.Ocean;
import com.eugeneze.service.OceanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "geoapp/api/oceans")
public class OceanController {
    private final OceanService service;

    @Autowired
    public OceanController(OceanService service) {
        this.service = service;
    }

    @GetMapping
    public List<Ocean> getOceans() { return service.getOceans(); }

    @GetMapping(path = "/{oceanId}")
    public Optional<Ocean> getOcean(@PathVariable("oceanId") int oceanId) {
        return service.getOcean(oceanId);
    }

    @DeleteMapping(path = "/{oceanId}")
    public void deleteOcean(@PathVariable("oceanId") int oceanId) {
        service.deleteOcean(oceanId);
    }

    @PostMapping
    public ResponseEntity addNewOcean(@RequestBody Ocean ocean) {
        Ocean savedOcean = service.addNewOcean(ocean);

        return ResponseEntity.created(UriComponentsBuilder.fromPath("/geoapp/api/oceans/{id}").buildAndExpand(savedOcean.getObjects()[0]).toUri()).build();
    }

    @PutMapping(path = "/{oceanId}")
    public void updateOcean(
            @PathVariable("oceanId") int oceanId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) int maxDepth,
            @RequestParam(required = false) int area
    ) {
        service.updateOcean(oceanId, name, maxDepth, area);
    }
}
