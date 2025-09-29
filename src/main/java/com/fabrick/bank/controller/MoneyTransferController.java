package com.fabrick.bank.controller;

import com.fabrick.bank.command.MoneyTransferCommand;
import com.fabrick.bank.controller.dto.moneytransfer.MoneyTransferDto;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import jakarta.validation.Valid;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.fabrick.bank.controller.PathConfig.*;

@Slf4j
@RestController
@RequestMapping(MONEY_TRANSFER_BASE_PATH)
public class MoneyTransferController {

    @Autowired
    private BeanFactory beanFactory;


    @PostMapping()
    public ResponseEntity<?> getAccountBalance(
            @Valid @RequestBody MoneyTransferDto moneyTransferRequestDto) {

        log.info("Money transfer: {}", moneyTransferRequestDto);

        MoneyTransferCommand command = beanFactory.getBean(MoneyTransferCommand.class, moneyTransferRequestDto);
        command.execute();

        return ResponseEntity.accepted().build();
    }
}
