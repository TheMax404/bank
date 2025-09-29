package com.fabrick.bank.command;

import com.fabrick.bank.controller.dto.account.AccountTransactionDto;
import com.fabrick.bank.exception.BankException;
import com.fabrick.bank.service.AccountService;
import com.fabrick.bank.service.dto.AccountTransactionResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;

import java.time.LocalDate;

import static com.fabrick.bank.exception.CodeError.INVALID_REQUEST;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AccountTransactionCommandTest {


    private AccountService accountService;


    @BeforeEach
    void setUp() {
        accountService = mock(AccountService.class);
    }

    @Test
    void execute_shouldReturnResponse_whenRequestIsValid() {
        AccountTransactionDto request = mock(AccountTransactionDto.class);
        AccountTransactionCommand command = new AccountTransactionCommand(request, accountService);
        when(request.getAccountId()).thenReturn(123L);
        when(request.getStartingDate()).thenReturn(LocalDate.now().minusDays(1).toString());
        when(request.getEndingDate()).thenReturn(LocalDate.now().toString());
        AccountTransactionResponseDto response = mock(AccountTransactionResponseDto.class);
        when(accountService.getTransactions(request)).thenReturn(response);

        AccountTransactionResponseDto result = command.execute();

        assertEquals(response, result);
        verify(accountService).getTransactions(request);
    }

    @Test
    void canExecute_shouldThrowException_whenStartDateAfterEndDate() {
        AccountTransactionDto request = mock(AccountTransactionDto.class);
        AccountTransactionCommand command = new AccountTransactionCommand(request, accountService);
        when(request.getStartingDate()).thenReturn("2024-06-10");
        when(request.getEndingDate()).thenReturn("2024-06-01");
        when(request.getAccountId()).thenReturn(123L);

        BankException ex = assertThrows(BankException.class, () -> command.canExecute(request));
        assertEquals(INVALID_REQUEST, ex.getCodeError());
        assertTrue(ex.getMessage().contains("Cannot get transactions for account"));
    }

    @Test
    void canExecute_shouldNotThrow_whenDatesAreValid() {
        AccountTransactionDto request = mock(AccountTransactionDto.class);
        AccountTransactionCommand command = new AccountTransactionCommand(request, accountService);
        when(request.getStartingDate()).thenReturn("2024-06-01");
        when(request.getEndingDate()).thenReturn("2024-06-10");

        assertDoesNotThrow(() -> command.canExecute(request));
    }
}
