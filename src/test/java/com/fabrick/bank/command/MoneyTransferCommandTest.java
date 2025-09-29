package com.fabrick.bank.command;
import com.fabrick.bank.controller.dto.moneytransfer.MoneyTransferDto;
import com.fabrick.bank.exception.BankException;
import com.fabrick.bank.service.MoneyTransferService;
import com.fabrick.bank.service.MoneyTransferServiceImpl;
import com.fabrick.bank.service.dto.MoneyTransferResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.BeanFactory;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.fabrick.bank.exception.CodeError.MONEY_TRANSFER_ERROR;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MoneyTransferCommandTest {

    private MoneyTransferService moneyTransferService;

    private Long accountId = 123L;


    @BeforeEach
    void setUp() {
        moneyTransferService = mock(MoneyTransferServiceImpl.class);
    }

    @Test
    void testExecute_Success() {
        MoneyTransferDto dto = new MoneyTransferDto();
        dto.setAmount(BigDecimal.TEN);
        dto.setAccountId(accountId);
        dto.setExecutionDate(LocalDate.now().toString());

        MoneyTransferResponseDto response = new MoneyTransferResponseDto();
        when(moneyTransferService.createMoneyTransfer(any())).thenReturn(response);

        MoneyTransferCommand command = new MoneyTransferCommand(dto, moneyTransferService);
        MoneyTransferResponseDto result = command.execute();

        assertNotNull(result);
        verify(moneyTransferService).createMoneyTransfer(dto);
    }

    @Test
    void testExecute_NullResponse_ThrowsException() {
        MoneyTransferDto dto = new MoneyTransferDto();
        dto.setAmount(BigDecimal.TEN);
        dto.setAccountId(accountId);
        dto.setExecutionDate(LocalDate.now().toString());

        when(moneyTransferService.createMoneyTransfer(any())).thenReturn(null);

        MoneyTransferCommand command = new MoneyTransferCommand(dto, moneyTransferService);
        BankException ex = assertThrows(BankException.class, command::execute);
        assertEquals(MONEY_TRANSFER_ERROR, ex.getCodeError());
    }

    @Test
    void testCanExecute_NegativeAmount_ThrowsException() {
        MoneyTransferDto dto = new MoneyTransferDto();
        dto.setAmount(BigDecimal.valueOf(-1));
        dto.setAccountId(accountId);
        dto.setExecutionDate(LocalDate.now().toString());

        MoneyTransferCommand command = new MoneyTransferCommand(dto, moneyTransferService);

        BankException ex = assertThrows(BankException.class, command::execute);
        assertEquals(MONEY_TRANSFER_ERROR, ex.getCodeError());
    }

    @Test
    void testCanExecute_ExecutionDateInFuture_ThrowsException() {
        MoneyTransferDto dto = new MoneyTransferDto();
        dto.setAmount(BigDecimal.TEN);
        dto.setAccountId(accountId);
        dto.setExecutionDate(LocalDate.now().plusDays(1).toString());

        MoneyTransferCommand command = new MoneyTransferCommand(dto, moneyTransferService);

        BankException ex = assertThrows(BankException.class, command::execute);
        assertEquals(MONEY_TRANSFER_ERROR, ex.getCodeError());
    }
}
