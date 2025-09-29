package com.fabrick.bank.service.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountBalanceResponseDto {
    String Date;
    BigDecimal balance;
    BigDecimal avaiableBalance;
    String currency;
}
