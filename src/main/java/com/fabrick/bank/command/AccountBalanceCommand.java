package com.fabrick.bank.command;

import com.fabrick.bank.exception.BankException;
import com.fabrick.bank.service.AccountService;
import com.fabrick.bank.service.dto.AccountBalanceResponseDto;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.fabrick.bank.exception.CodeError.BALANCE_NOT_FOUND;

@Slf4j
@Scope("prototype")
@Component
public class AccountBalanceCommand implements BaseCommand<Long, AccountBalanceResponseDto> {

    private final AccountService accountService;
    private final Long accountId;

    public AccountBalanceCommand(Long accountId, AccountService accountService) {
        this.accountId = accountId;
        this.accountService = accountService;
    }

    @Override
    public AccountBalanceResponseDto execute() {
        AccountBalanceResponseDto balanceDto = accountService.getAccountBalance(accountId);
            if (Objects.nonNull(balanceDto)) {
                return balanceDto;
            }
            log.error("No balance found for account: {}", accountId);
            throw new BankException(BALANCE_NOT_FOUND, "No balance found for account: " + accountId);
    }
}
