package com.eugeneze.springcontrollers;

import com.eugeneze.models.Currency;
import com.eugeneze.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "geoapp/api/currencies")
public class CurrencyController {
    private final CurrencyService service;

    @Autowired
    public CurrencyController(CurrencyService service) {
        this.service = service;
    }

    @GetMapping
    public List<Currency> getCurrencies() { return service.getCurrencies(); }

    @GetMapping(path = "/{currencyId}")
    public Optional<Currency> getCurrency(@PathVariable("currencyId") int currencyId) {
        return service.getCurrency(currencyId);
    }

    @DeleteMapping(path = "/{currencyId}")
    public void deleteCurrency(@PathVariable("currencyId") int currencyId) {
        service.deleteCurrency(currencyId);
    }

    @PostMapping
    public ResponseEntity addNewCurrency(@RequestBody Currency currency) {
        Currency savedCurrency = service.addNewCurrency(currency);

        return ResponseEntity.created(UriComponentsBuilder.fromPath("/geoapp/api/currencies/{id}").buildAndExpand(savedCurrency.getObjects()[0]).toUri()).build();
    }

    @PutMapping(path = "/{currencyId}")
    public void updateCurrency(
            @PathVariable("currencyId") int currencyId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String code
    ) {
        service.updateCurrency(currencyId, name, code);
    }
}
