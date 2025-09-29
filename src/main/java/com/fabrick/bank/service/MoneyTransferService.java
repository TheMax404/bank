package com.fabrick.bank.service;

import com.fabrick.bank.controller.dto.moneytransfer.MoneyTransferDto;
import com.fabrick.bank.service.dto.MoneyTransferRequestDto;
import com.fabrick.bank.service.dto.MoneyTransferResponseDto;

public interface MoneyTransferService {
    MoneyTransferResponseDto createMoneyTransfer(MoneyTransferDto request);
}
