package com.eugeneze.springcontrollers;

import com.eugeneze.models.Mountain;
import com.eugeneze.service.MountainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "geoapp/api/mountains")
public class MountainController {
    private final MountainService service;

    @Autowired
    public MountainController(MountainService service) {
        this.service = service;
    }

    @GetMapping
    public List<Mountain> getMountains() { return service.getMountains(); }

    @GetMapping(path = "/{mountainId}")
    public Optional<Mountain> getMountain(@PathVariable("mountainId") int mountainId) {
        return service.getMountain(mountainId);
    }

    @DeleteMapping(path = "/{mountainId}")
    public void deleteMountain(@PathVariable("mountainId") int mountainId) {
        service.deleteMountain(mountainId);
    }

    @PostMapping
    public ResponseEntity addNewMountain(@RequestBody Mountain mountain) {
        Mountain savedMountain = service.addNewMountain(mountain);

        HttpHeaders headers = new HttpHeaders();
        headers.add("location", "localhost:8080/geoapp/api/mountains/" + savedMountain.getObjects()[0]);

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{mountainId}")
    public void updateMountain(
            @PathVariable("mountainId") int mountainId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) int height
    ) {
        service.updateMountain(mountainId, name, height);
    }
}
