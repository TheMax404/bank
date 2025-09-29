package com.fabrick.bank.service.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class MoneyTransferResponseDto {
    private String moneyTransferId;
    private String status;
    private String direction;
    private Creditor creditor;
    private Debtor debtor;
    private String cro;
    private String uri;
    private String trn;
    private String description;
    private String createdDatetime;
    private String accountedDatetime;
    private String debtorValueDate;
    private String creditorValueDate;
    private Amount amount;
    private Boolean isUrgent;
    private Boolean isInstant;
    private String feeType;
    private String feeAccountId;
    private List<Fee> fees;
    private Boolean hasTaxRelief;

    @Data
    public static class Creditor {
        private String name;
        private Account account;
        private Address address;

        @Data
        public static class Account {
            private String accountCode;
            private String bicCode;
        }

        @Data
        public static class Address {
            private String address;
            private String city;
            private String countryCode;
        }
    }

    @Data
    public static class Debtor {
        private String name;
        private Account account;

        @Data
        public static class Account {
            private String accountCode;
            private String bicCode;
        }
    }

    @Data
    public static class Amount {
        private BigDecimal debtorAmount;
        private String debtorCurrency;
        private BigDecimal creditorAmount;
        private String creditorCurrency;
        private String creditorCurrencyDate;
        private BigDecimal exchangeRate;
    }

    @Data
    public static class Fee {
        private String feeCode;
        private String description;
        private BigDecimal amount;
        private String currency;
    }
}
