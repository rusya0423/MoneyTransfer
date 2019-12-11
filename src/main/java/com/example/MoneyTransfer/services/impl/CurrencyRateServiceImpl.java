package com.example.MoneyTransfer.services.impl;

import com.example.MoneyTransfer.model.Currency;
import com.example.MoneyTransfer.model.CurrencyRate;
import com.example.MoneyTransfer.repository.CurrencyRateRepository;
import com.example.MoneyTransfer.services.CurrencyRateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@AllArgsConstructor
@Service
public class CurrencyRateServiceImpl implements CurrencyRateService {
    private CurrencyRateRepository currencyRateRepository;
    @Override
    public CurrencyRate findByCreatedDateCurFromAndCurTo(LocalDateTime date, Currency currencyFrom, Currency currencyTo) {
        return currencyRateRepository.findByCreatedDateIsAfterAndCurrencyFromAAndCurrencyTo(date, currencyFrom, currencyTo).orElse(null);
    }
}
