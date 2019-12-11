package com.example.MoneyTransfer.services;

import com.example.MoneyTransfer.model.Account;
import com.example.MoneyTransfer.model.Transaction;

import java.math.BigDecimal;

public interface TransactionService {
    void sendMoney(String from, String to, BigDecimal sum);
    Transaction update(Transaction transaction);
    Transaction getTransaction(Account from, Account to, BigDecimal sum);
}
