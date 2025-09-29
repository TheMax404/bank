package com.fabrick.bank.controller.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountBalanceResponseModel {
    String Date;
    BigDecimal balance;
    BigDecimal avaiableBalance;
    String currency;
}
