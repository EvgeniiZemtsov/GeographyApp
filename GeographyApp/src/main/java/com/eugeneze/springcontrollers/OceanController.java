package com.eugeneze.springcontrollers;

import com.eugeneze.models.Mountain;
import com.eugeneze.models.Ocean;
import com.eugeneze.service.OceanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        HttpHeaders headers = new HttpHeaders();
        headers.add("location", "localhost:8080/geoapp/api/oceans/" + savedOcean.getObjects()[0]);

        return new ResponseEntity(headers, HttpStatus.CREATED);
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
