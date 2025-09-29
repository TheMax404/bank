package com.fabrick.bank.controller.model;

import com.fabrick.bank.service.dto.AccountTransactionResponseDto;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.List;

@Data
public class AccountTransactionResponseModel {

    private List<Transaction> transactions;

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
