package com.fabrick.bank.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class FabrickWebClientConfig {

    @Bean
    public WebClient fabrickWebClient(FabrickProperties props) {
        return WebClient.builder()
                .baseUrl(props.getBaseUrl())
                .defaultHeader("Api-Key", props.getApiKey())
                .defaultHeader("Auth-Schema", props.getAuthSchema())
                .build();
    }
}
