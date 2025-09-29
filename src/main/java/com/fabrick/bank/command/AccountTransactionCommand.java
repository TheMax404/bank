package com.fabrick.bank.command;

import com.fabrick.bank.controller.dto.account.AccountTransactionDto;
import com.fabrick.bank.exception.BankException;
import com.fabrick.bank.service.AccountService;
import com.fabrick.bank.service.dto.AccountTransactionResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static com.fabrick.bank.exception.CodeError.INVALID_REQUEST;
import static com.fabrick.bank.exception.CodeError.TRANSACTION_NOT_FOUND;

@Slf4j
@Scope("prototype")
@Component
public class AccountTransactionCommand implements BaseCommand<AccountTransactionDto, AccountTransactionResponseDto> {

    private final AccountService accountService;
    private final AccountTransactionDto request;

    public AccountTransactionCommand(AccountTransactionDto request, AccountService accountService) {
        this.accountService = accountService;
        this.request = request;
    }

    @Override
    public AccountTransactionResponseDto execute() {
        canExecute(request);
        return accountService.getTransactions(request);
    }

    public void canExecute(AccountTransactionDto request) {
        LocalDate dateFrom = LocalDate.parse(request.getStartingDate());
        LocalDate dateTo = LocalDate.parse(request.getEndingDate());

        if (dateFrom.isAfter(dateTo)) {
            log.error("Cannot get transactions for account: {} from {} to {}", request.getAccountId(), request.getStartingDate(), request.getEndingDate());
            throw new BankException(INVALID_REQUEST, String.format("Cannot get transactions for account with startDate %s and endDate %s : %s", request.getStartingDate(), request.getEndingDate(), request.getAccountId()));        }
    }
}
