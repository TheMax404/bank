package com.fabrick.bank.service.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class AccountTransactionResponseDto {

    private List<Transaction> list;

    @Data
    public static class Transaction {
        private String transactionId;
        private String operationId;
        private String accountingDate;
        private String valueDate;
        private Type type;
        private BigDecimal amount;
        private String currency;
        private String description;

        @Data
        public static class Type {
            private String enumeration;
            private String value;
        }
    }
}
