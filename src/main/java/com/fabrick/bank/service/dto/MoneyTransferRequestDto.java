package com.fabrick.bank.service.dto;

import lombok.Data;


import java.math.BigDecimal;

@Data
public class MoneyTransferRequestDto {

    private Creditor creditor;
    private String executionDate;
    private String uri;
    private String description;
    private BigDecimal amount;
    private String currency;
    private Boolean isUrgent;
    private Boolean isInstant;
    private String feeType;
    private String feeAccountId;
    private TaxRelief taxRelief;

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
    public static class TaxRelief {
        private String taxReliefId;
        private Boolean isCondoUpgrade;
        private String creditorFiscalCode;
        private String beneficiaryType;
        private NaturalPersonBeneficiary naturalPersonBeneficiary;
        private LegalPersonBeneficiary legalPersonBeneficiary;

        @Data
        public static class NaturalPersonBeneficiary {
            private String fiscalCode1;
            private String fiscalCode2;
            private String fiscalCode3;
            private String fiscalCode4;
            private String fiscalCode5;
        }

        @Data
        public static class LegalPersonBeneficiary {
            private String fiscalCode;
            private String legalRepresentativeFiscalCode;
        }
    }
}

