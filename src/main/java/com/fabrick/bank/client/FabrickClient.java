package com.fabrick.bank.client;

import com.fabrick.bank.client.config.FabrickProperties;
import com.fabrick.bank.exception.ErrorResponse;
import com.fabrick.bank.exception.FabrickWebClientException;
import com.fabrick.bank.service.dto.AccountBalanceResponseDto;
import com.fabrick.bank.service.dto.AccountTransactionResponseDto;
import com.fabrick.bank.service.dto.MoneyTransferRequestDto;
import com.fabrick.bank.service.dto.MoneyTransferResponseDto;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.HttpStatusCode;
import reactor.core.publisher.Mono;

@Component
public class FabrickClient {

    private final WebClient webClient;
    private final FabrickProperties props;

    public FabrickClient(WebClient fabrickWebClient, FabrickProperties props) {
        this.webClient = fabrickWebClient;
        this.props = props;
    }

    public AccountBalanceResponseDto getBalance(Long accountId) {
        return webClient.get()
                .uri(props.getPaths().getBalance(), accountId)
                .header("X-Time-Zone",props.getTimeZone())
                .retrieve()
                .onStatus(HttpStatusCode::isError, this::mapError)
                .bodyToMono(AccountBalanceResponseDto.class)
                .block();
    }

    public AccountTransactionResponseDto getTransactions(Long accountId, String from, String to) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(props.getPaths().getTransactions())
                        .queryParam("fromAccountingDate", from)
                        .queryParam("toAccountingDate", to)
                        .build(accountId))
                .header("X-Time-Zone",props.getTimeZone())
                .retrieve()
                .onStatus(HttpStatusCode::isError, this::mapError)
                .bodyToMono(AccountTransactionResponseDto.class)
                .block();
    }

    public MoneyTransferResponseDto createMoneyTransfer(Long accountId, MoneyTransferRequestDto request) {
        return webClient.post()
                .uri(props.getPaths().getMoneyTransfer(), accountId)
                .bodyValue(request)
                .header("X-Time-Zone",props.getTimeZone())
                .retrieve()
                .onStatus(HttpStatusCode::isError, this::mapError)
                .bodyToMono(MoneyTransferResponseDto.class)
                .block();
    }

    private Mono<? extends Throwable> mapError(org.springframework.web.reactive.function.client.ClientResponse response) {
        return response.bodyToMono(ErrorResponse.class)
                .map(FabrickWebClientException::new);
    }
}
