package com.example.MoneyTransfer.services;

import com.example.MoneyTransfer.model.Account;

public interface AccountService {
    Account findByAcNumber(String number);
    Account update(Account account);
}
