package com.example.MoneyTransfer.services;

import com.example.MoneyTransfer.model.Currency;
import com.example.MoneyTransfer.model.CurrencyRate;

import java.time.LocalDateTime;
import java.util.Optional;

public interface CurrencyRateService {
    CurrencyRate findByCreatedDateCurFromAndCurTo(LocalDateTime date, Currency currencyFrom, Currency currencyTo);
}
