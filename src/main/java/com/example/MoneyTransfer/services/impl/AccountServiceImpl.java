package com.example.MoneyTransfer.services.impl;

import com.example.MoneyTransfer.model.Account;
import com.example.MoneyTransfer.repository.AccountRepository;
import com.example.MoneyTransfer.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;
    @Override
    public Account findByAcNumber(String number) {
        return accountRepository.findByAccount_number(number).orElse(null);
    }

    @Override
    public Account update(Account account){
        if(account.getId()!=null){
            return accountRepository.save(account);
        }
            return null;
    }
}
