package com.fabrick.bank.controller.dto.account;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountTransactionDto {
    Long accountId;
    String startingDate;
    String endingDate;
}
