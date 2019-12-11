package com.example.MoneyTransfer.repository;

import com.example.MoneyTransfer.model.Currency;
import com.example.MoneyTransfer.model.CurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
@Repository
public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, Long> {
    Optional<CurrencyRate> findByCreatedDateIsAfterAndCurrencyFromAAndCurrencyTo(LocalDateTime date, Currency currencyFrom, Currency currencyTo);
}
