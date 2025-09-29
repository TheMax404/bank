package com.fabrick.bank.service;

import com.fabrick.bank.client.FabrickClient;
import com.fabrick.bank.controller.dto.account.AccountTransactionDto;
import com.fabrick.bank.service.dto.AccountBalanceResponseDto;
import com.fabrick.bank.service.dto.AccountTransactionResponseDto;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AccountServiceTest {

    @Mock
    private FabrickClient fabrickClient;

    @InjectMocks
    private AccountServiceImpl accountService;

    public AccountServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    private static final Long accountId = 123L;
    private static final String fromDate = "2023-01-01";
    private static final String toDate = "2023-01-31";


    @Test
    void testGetBalance() {
        AccountBalanceResponseDto response = new AccountBalanceResponseDto();
        when(fabrickClient.getBalance(accountId)).thenReturn(response);

        AccountBalanceResponseDto result = accountService.getAccountBalance(accountId);

        assertEquals(response, result);
        verify(fabrickClient).getBalance(accountId);
    }

    @Test
    void testGetTransactions() {
        AccountTransactionDto request = new AccountTransactionDto(accountId, fromDate, toDate);

        AccountTransactionResponseDto response = new AccountTransactionResponseDto();
        when(fabrickClient.getTransactions(
                request.getAccountId(),
                request.getStartingDate(),
                request.getEndingDate()
        )).thenReturn(response);

        AccountTransactionResponseDto result = accountService.getTransactions(request);

        assertEquals(response, result);
        verify(fabrickClient).getTransactions(
                request.getAccountId(),
                request.getStartingDate(),
                request.getEndingDate()
        );
    }
}