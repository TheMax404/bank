package com.fabrick.bank.controller;

public interface PathConfig {

    /** Account Paths */
    String ACCOUNT_BASE_PATH = "/api/v1/banking/account";
    String ACCOUNT_BALANCE_PATH = "/{accountId}/balance";
    String ACCOUNT_TRANSACTIONS_PATH = "/{accountId}/transactions";

    /** Money Transfer Paths */
    String MONEY_TRANSFER_BASE_PATH = "/api/v1/banking/money-transfer";
}
