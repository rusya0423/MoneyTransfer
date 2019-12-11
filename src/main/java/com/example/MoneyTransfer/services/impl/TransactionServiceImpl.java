package com.example.MoneyTransfer.services.impl;

import com.example.MoneyTransfer.model.Account;
import com.example.MoneyTransfer.model.CurrencyRate;
import com.example.MoneyTransfer.model.Transaction;
import com.example.MoneyTransfer.model.TransactionStatus;
import com.example.MoneyTransfer.repository.TransactionRepository;
import com.example.MoneyTransfer.services.AccountService;
import com.example.MoneyTransfer.services.CurrencyRateService;
import com.example.MoneyTransfer.services.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private AccountService accountService;
    private CurrencyRateService currencyRateService;
    private TransactionRepository transactionRepository;

    @Override
    public void sendMoney(String from, String to, BigDecimal sum) {
        Account sender = accountService.findByAcNumber(from);
        Account receiver = accountService.findByAcNumber(to);

        BigDecimal balance = sender.getBalance().subtract(sender.getBlockedBalance());
        BigDecimal totalSum = BigDecimal.ZERO;

        if(sender.getCurrency() == receiver.getCurrency()){
            totalSum = sum;
        }else {
            CurrencyRate currencyRate = currencyRateService.findByCreatedDateCurFromAndCurTo(LocalDateTime.of(LocalDate.now(), LocalTime.MIN), sender.getCurrency(), receiver.getCurrency());
            totalSum = sum.multiply(currencyRate.getRate());
        }

        Transaction transaction = this.getTransaction(sender, receiver, totalSum);
            if(balance.compareTo(totalSum) >= 0){
                transaction.setTransactionStatus(TransactionStatus.APPROVED);
                sender.setBlockedBalance(sender.getBlockedBalance().add(totalSum));
                sender.setBalance(sender.getBalance().subtract(totalSum));
                receiver.setBalance(receiver.getBalance().add(totalSum));
                accountService.update(sender);
                accountService.update(receiver);
        }else {
                transaction.setTransactionStatus(TransactionStatus.FAILED);
            }
                this.update(transaction);
    }

    @Override
    public Transaction update(Transaction transaction) {
        if(transaction.getId() != null){
            return transactionRepository.save(transaction);
        }
            return null;
    }

    @Override
    public Transaction getTransaction(Account from, Account to, BigDecimal sum) {
        Transaction transaction = new Transaction();
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setCurrency(from.getCurrency());
        transaction.setReceiverId(to.getId());
        transaction.setSenderId(from.getId());
        transaction.setSum(sum);
        return transaction;
    }
}
