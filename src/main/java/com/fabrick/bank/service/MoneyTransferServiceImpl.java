package com.fabrick.bank.service;

import com.fabrick.bank.client.FabrickClient;
import com.fabrick.bank.controller.dto.moneytransfer.MoneyTransferDto;
import com.fabrick.bank.service.dto.MoneyTransferRequestDto;
import com.fabrick.bank.service.dto.MoneyTransferResponseDto;
import org.springframework.stereotype.Service;

@Service
public class MoneyTransferServiceImpl implements MoneyTransferService {


    private final FabrickClient fabrickClient;

    public MoneyTransferServiceImpl(FabrickClient fabrickClient) {
        this.fabrickClient = fabrickClient;
    }


    @Override
    public MoneyTransferResponseDto createMoneyTransfer(MoneyTransferDto request) {
        MoneyTransferRequestDto moneyTransferRequest = new MoneyTransferRequestDto();

        moneyTransferRequest.setAmount(request.getAmount());
        moneyTransferRequest.setCurrency(request.getCurrency());
        moneyTransferRequest.setDescription(request.getDescription());

        MoneyTransferRequestDto.Creditor creditor = new MoneyTransferRequestDto.Creditor();
        creditor.setName(request.getCreditorName());
        MoneyTransferRequestDto.Creditor.Account account = new MoneyTransferRequestDto.Creditor.Account();
        account.setAccountCode(request.getCreditorAccount());

        creditor.setAccount(account);
        moneyTransferRequest.setCreditor(creditor);


        return fabrickClient.createMoneyTransfer(request.getAccountId(), moneyTransferRequest);

    }
}
