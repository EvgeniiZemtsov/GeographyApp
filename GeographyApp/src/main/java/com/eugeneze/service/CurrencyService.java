package com.eugeneze.service;

import com.eugeneze.models.City;
import com.eugeneze.models.Currency;
import com.eugeneze.springrepositories.SpringCurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CurrencyService {
    private final SpringCurrencyRepository repository;

    @Autowired
    public CurrencyService(SpringCurrencyRepository repository) {
        this.repository = repository;
    }

    public List<Currency> getCurrencies() {
        return repository.findAll();
    }

    public Optional<Currency> getCurrency(int currencyId) {
        boolean exists = repository.existsById(currencyId);

        if (!exists) {
            throw new IllegalStateException("Currency with id = " + currencyId + " doesn't exist");
        }

        return repository.findById(currencyId);
    }

    public Currency addNewCurrency(Currency currency) {
        Optional<Currency> currencyOptional = repository.findCurrencyByName((String) currency.getObjects()[1]);

        if (currencyOptional.isPresent()) {
            throw new IllegalStateException("Currency already exists");
        }

        return repository.save(currency);
    }

    public void deleteCurrency(int currencyId) {
        boolean exists = repository.existsById(currencyId);

        if (!exists) {
            throw new IllegalStateException("Currency with id = " + currencyId + " doesn't exist");
        }

        repository.deleteById(currencyId);
    }

    @Transactional
    public void updateCurrency(int currencyId, String name, String code) {
        Currency currency = repository.findById(currencyId).orElseThrow(() -> new IllegalStateException("Currency with id = " + currencyId + "doesn't exist"));

        if (name != null && name.length() > 0 && !Objects.equals(currency.getObjects()[1], name)) {
            currency.setName(name);
        }

        if (code != null && code.length() > 0 && !Objects.equals(currency.getObjects()[2], code)) {
            currency.setCode(code);
        }
    }

}
