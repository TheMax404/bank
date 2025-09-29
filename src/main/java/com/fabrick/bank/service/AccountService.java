package com.fabrick.bank.service;

import com.fabrick.bank.controller.dto.account.AccountTransactionDto;
import com.fabrick.bank.service.dto.AccountBalanceResponseDto;
import com.fabrick.bank.service.dto.AccountTransactionResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    AccountBalanceResponseDto getAccountBalance(Long accountId);
    AccountTransactionResponseDto getTransactions(AccountTransactionDto request);}
