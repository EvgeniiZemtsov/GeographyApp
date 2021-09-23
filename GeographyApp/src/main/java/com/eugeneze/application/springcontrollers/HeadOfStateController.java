package com.eugeneze.application.springcontrollers;

import com.eugeneze.models.HeadOfState;
import com.eugeneze.application.service.HeadOfStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "geoapp/api/headsofstates")
public class HeadOfStateController {
    private final HeadOfStateService service;

    @Autowired
    public HeadOfStateController(HeadOfStateService service) {
        this.service = service;
    }

    @GetMapping
    public List<HeadOfState> getHeadsOfStates() { return service.getHeadsOfStates(); }

    @GetMapping(path = "/{headOfStateId}")
    public Optional<HeadOfState> getCity(@PathVariable("headOfStateId") int headOfStateId) {
        return service.getHeadOfState(headOfStateId);
    }

    @DeleteMapping(path = "/{headOfStateId}")
    public void deleteCity(@PathVariable("headOfStateId") int headOfStateId) {
        service.deleteHeadOfState(headOfStateId);
    }

    @PostMapping
    public ResponseEntity addNewCity(@RequestBody HeadOfState headOfState) {
        HeadOfState savedHeadOfState = service.addNewHeadOfState(headOfState);

        return ResponseEntity.created(UriComponentsBuilder.fromPath("/geoapp/api/headsofstates/{id}").buildAndExpand(savedHeadOfState.getObjects()[0]).toUri()).build();
    }

    @PutMapping(path = "/{headOfStateId}")
    public void updateCountry(
            @PathVariable("headOfStateId") int headOfStateId,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String title
    ) {
        service.updateHeadOfState(headOfStateId, firstName, lastName, title);
    }
}
