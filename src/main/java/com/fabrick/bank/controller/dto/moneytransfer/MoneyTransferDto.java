package com.fabrick.bank.controller.dto.moneytransfer;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.math.BigDecimal;

@Data
public class MoneyTransferDto {
   @NotNull
   private Long accountId;
   @NotNull
   private String creditorName;
   @NotNull
   private String creditorAccount;
   @NotNull
   private String description;
   @NotNull
   private String currency;
   @NotNull
   private BigDecimal amount;
   @NotNull
   private String executionDate;
}
