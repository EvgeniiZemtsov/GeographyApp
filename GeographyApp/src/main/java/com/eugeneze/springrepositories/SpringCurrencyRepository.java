package com.eugeneze.springrepositories;

import com.eugeneze.models.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringCurrencyRepository extends JpaRepository<Currency, Integer> {

    Optional<Currency> findCurrencyByName(String currencyName);
}
