package com.fabrick.bank.client.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "fabrick.sandbox")
public class FabrickProperties {
    private String baseUrl;
    private String apiKey;
    private String authSchema;
    private String timeZone;
    private Long accountId;
    private Paths paths;

    @Getter
    @Setter
    public static class Paths {
        private String balance;
        private String transactions;
        private String moneyTransfer;

    }
}
