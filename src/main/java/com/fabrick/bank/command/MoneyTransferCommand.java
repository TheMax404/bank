package com.fabrick.bank.command;

import com.fabrick.bank.controller.dto.moneytransfer.MoneyTransferDto;
import com.fabrick.bank.exception.BankException;
import com.fabrick.bank.service.AccountService;
import com.fabrick.bank.service.MoneyTransferService;
import com.fabrick.bank.service.dto.AccountBalanceResponseDto;
import com.fabrick.bank.service.dto.MoneyTransferResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import static com.fabrick.bank.exception.CodeError.BALANCE_NOT_FOUND;
import static com.fabrick.bank.exception.CodeError.MONEY_TRANSFER_ERROR;

@Slf4j
@Scope("prototype")
@Component
public class MoneyTransferCommand implements BaseCommand<MoneyTransferDto, MoneyTransferResponseDto> {

    private final MoneyTransferService moneyTransferService;
    private final MoneyTransferDto request;

    public MoneyTransferCommand(MoneyTransferDto request, MoneyTransferService moneyTransferService) {
        this.moneyTransferService = moneyTransferService;
        this.request = request;
    }

    @Override
    public MoneyTransferResponseDto execute() {
        canExecute(request);
        MoneyTransferResponseDto responseDto = moneyTransferService.createMoneyTransfer(request);
            if (Objects.nonNull(responseDto)) {
                return responseDto;
            }
            throw new BankException(MONEY_TRANSFER_ERROR, "Money transfer error");
    }

    protected void canExecute(MoneyTransferDto request) {
        if (request.getAmount().compareTo(BigDecimal.ZERO) < 0) {
            log.error("Cannot make a money transfer for account: {} with amount: {}", request.getAccountId(), request.getAmount());
            throw new BankException(MONEY_TRANSFER_ERROR, String.format("Cannot make a money transfer for account: %s with amount: %s", request.getAccountId(), request.getAmount()));
        }
        if(LocalDate.now().isBefore(LocalDate.parse(request.getExecutionDate()))) {
            log.error("Cannot make a money transfer for account: {} with execution date in the past: {}", request.getAccountId(), request.getExecutionDate());
            throw new BankException(MONEY_TRANSFER_ERROR, String.format("Cannot make a money transfer for account: %s with execution date in the past: %s", request.getAccountId(), request.getExecutionDate()));
        }
    }
}
