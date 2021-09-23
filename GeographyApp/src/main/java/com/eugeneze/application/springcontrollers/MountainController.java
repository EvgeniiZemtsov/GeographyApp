package com.eugeneze.application.springcontrollers;

import com.eugeneze.models.Mountain;
import com.eugeneze.application.service.MountainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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

        return ResponseEntity.created(UriComponentsBuilder.fromPath("/geoapp/api/mountains/{id}").buildAndExpand(savedMountain.getObjects()[0]).toUri()).build();
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
