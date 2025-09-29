package com.fabrick.bank.controller;

import com.fabrick.bank.assembler.AccountBalanceAssembler;
import com.fabrick.bank.assembler.AccountTransactionAssembler;
import com.fabrick.bank.command.AccountBalanceCommand;
import com.fabrick.bank.command.AccountTransactionCommand;
import com.fabrick.bank.controller.dto.account.AccountTransactionDto;
import com.fabrick.bank.controller.model.AccountBalanceResponseModel;
import com.fabrick.bank.controller.model.AccountTransactionResponseModel;
import com.fabrick.bank.service.dto.AccountBalanceResponseDto;
import com.fabrick.bank.service.dto.AccountTransactionResponseDto;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.fabrick.bank.controller.PathConfig.*;

@Slf4j
@RestController
@RequestMapping(ACCOUNT_BASE_PATH)
public class AccountController {

    @Autowired
    private BeanFactory beanFactory;
    @Autowired
    private AccountBalanceAssembler accountBalanceAssembler;
    @Autowired
    private AccountTransactionAssembler accountTransactionAssembler;

    @GetMapping(ACCOUNT_BALANCE_PATH)
    public ResponseEntity<AccountBalanceResponseModel> getAccountBalance(
            @PathVariable @NotNull Long accountId) {

        log.info("Get balance for account: {}", accountId);
        AccountBalanceCommand command = beanFactory.getBean(AccountBalanceCommand.class, accountId);
        AccountBalanceResponseDto balanceDto = command.execute();
        AccountBalanceResponseModel modelResponse = accountBalanceAssembler.toModel(balanceDto);
        return ResponseEntity.ok(modelResponse);
    }

    @GetMapping(ACCOUNT_TRANSACTIONS_PATH)
    public ResponseEntity<AccountTransactionResponseModel> getTransactions(
            @PathVariable @NotNull Long accountId,
            @RequestParam(required = false) String dateFrom,
            @RequestParam(required = false) String dateTo) {

        log.info("Get transactions for account: {} from {} to {}", accountId, dateFrom, dateTo);

        AccountTransactionDto requestDto = new AccountTransactionDto(accountId, dateFrom, dateTo);
        AccountTransactionCommand command = beanFactory.getBean(AccountTransactionCommand.class, requestDto);
        AccountTransactionResponseDto transactionsDto = command.execute();
        AccountTransactionResponseModel modelResponse = accountTransactionAssembler.toModel(transactionsDto);

        return ResponseEntity.ok(modelResponse);
    }
}
