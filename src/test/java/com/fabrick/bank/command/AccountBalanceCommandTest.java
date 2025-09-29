package com.fabrick.bank.command;

import com.fabrick.bank.client.FabrickClient;
import com.fabrick.bank.exception.BankException;
import com.fabrick.bank.service.AccountService;
import com.fabrick.bank.service.AccountServiceImpl;
import com.fabrick.bank.service.dto.AccountBalanceResponseDto;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import static com.fabrick.bank.exception.CodeError.BALANCE_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AccountBalanceCommandTest {

    @Mock
    private AccountService accountService;
    private FabrickClient fabrickClient;

    private static final Long accountId = 123L;


    @BeforeEach
    void setUp() {
        accountService = mock(AccountServiceImpl.class);
        fabrickClient = mock(FabrickClient.class);
    }

    @Test
    void execute_shouldReturnBalance_whenBalanceExists() {
        AccountBalanceCommand accountBalanceCommand = new AccountBalanceCommand(accountId, accountService);
        when(fabrickClient.getBalance(accountId)).thenReturn(null);
        AccountBalanceResponseDto dto = new AccountBalanceResponseDto();
        when(accountService.getAccountBalance(accountId)).thenReturn(dto);

        AccountBalanceResponseDto result = accountBalanceCommand.execute();

        assertNotNull(result);
        assertEquals(dto, result);
        verify(accountService, times(1)).getAccountBalance(accountId);
    }

    @Test
    void execute_shouldThrowException_whenBalanceNotFound() {
        AccountBalanceCommand accountBalanceCommand = new AccountBalanceCommand(accountId, accountService);
        when(fabrickClient.getBalance(accountId)).thenReturn(new AccountBalanceResponseDto());
        when(accountService.getAccountBalance(accountId)).thenReturn(null);

        BankException ex = assertThrows(BankException.class, accountBalanceCommand::execute);
        assertEquals(BALANCE_NOT_FOUND, ex.getCodeError());
        assertTrue(ex.getMessage().contains(accountId.toString()));
        verify(accountService, times(1)).getAccountBalance(accountId);
    }
}
