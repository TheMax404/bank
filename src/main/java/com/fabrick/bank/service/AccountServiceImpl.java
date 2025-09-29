package com.fabrick.bank.service;

import com.fabrick.bank.client.FabrickClient;
import com.fabrick.bank.controller.dto.account.AccountTransactionDto;
import com.fabrick.bank.service.dto.AccountBalanceResponseDto;
import com.fabrick.bank.service.dto.AccountTransactionResponseDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AccountServiceImpl implements AccountService {


    private final FabrickClient fabrickClient;

    public AccountServiceImpl(FabrickClient fabrickClient) {
        this.fabrickClient = fabrickClient;
    }

    @Override
    public AccountBalanceResponseDto getAccountBalance(Long accountId) {
        return fabrickClient.getBalance(accountId);
    }

    @Override
    public AccountTransactionResponseDto getTransactions(AccountTransactionDto request) {
        return fabrickClient.getTransactions(request.getAccountId(), request.getStartingDate(), request.getEndingDate());
    }
}
