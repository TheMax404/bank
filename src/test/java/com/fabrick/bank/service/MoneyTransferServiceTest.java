package com.fabrick.bank.service;

import com.fabrick.bank.client.FabrickClient;
import com.fabrick.bank.controller.dto.moneytransfer.MoneyTransferDto;
import com.fabrick.bank.service.dto.MoneyTransferRequestDto;
import com.fabrick.bank.service.dto.MoneyTransferResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MoneyTransferServiceTest {

    @Mock
    private FabrickClient fabrickClient;

    @InjectMocks
    private MoneyTransferServiceImpl moneyTransferService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private static final Long accountId = 123L;


    @Test
    void testCreateMoneyTransfer() {
        MoneyTransferDto request = new MoneyTransferDto();
        request.setAccountId(accountId);
        MoneyTransferRequestDto requestDto = new MoneyTransferRequestDto();
        MoneyTransferResponseDto response = new MoneyTransferResponseDto();
        when(fabrickClient.createMoneyTransfer(eq(accountId), any(MoneyTransferRequestDto.class))).thenReturn(response);

        moneyTransferService.createMoneyTransfer(request);
        verify(fabrickClient).createMoneyTransfer(eq(accountId), any(MoneyTransferRequestDto.class));    }

}