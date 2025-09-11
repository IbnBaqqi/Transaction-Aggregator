package com.salausmart.transactionaggregator;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@Service
public class AggregateService {

    private final RestClient restClient;

    public AggregateService(RestClient restClient) {
        this.restClient = restClient;
    }

    private final String accountUrl1 = "http://localhost:8888";
    private final String accountUrl2 = "http://localhost:8889";

    public List<AccountResponseDto> aggregate(String account) {

        final var accounts1 = getAccountResponse(accountUrl1, account);
        final var accounts2 = getAccountResponse(accountUrl2, account);

        return sortAccounts(accounts1, accounts2);
    }

    private List<AccountResponseDto> getAccountResponse(String baseUrl, String account) {
        return restClient.get()
                .uri(baseUrl + "/transactions?account=" + account)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    private List<AccountResponseDto> sortAccounts(List<AccountResponseDto> account1, List<AccountResponseDto> account2) {

        return Stream.concat(account1.stream(), account2.stream())
                .sorted(Comparator.comparing(AccountResponseDto::getTimestamp).reversed())
                .toList();
    }
}
