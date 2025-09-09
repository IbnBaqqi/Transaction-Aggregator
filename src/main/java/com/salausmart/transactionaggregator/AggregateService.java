package com.salausmart.transactionaggregator;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class AggregateService {

    private final RestClient restClient;

    public AggregateService(RestClient restClient) {
        this.restClient = restClient;
    }

    public List<AccountResponseDto> aggregate(String account) {

        List<AccountResponseDto> accounts1 = restClient.get()
                .uri("http://localhost:8889/transactions?account=" + account)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

        List<AccountResponseDto> accounts2 = restClient.get()
                .uri("http://localhost:8889/transactions?account=" + account)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
        var sortedAccount = sortAccounts(accounts1, accounts2);
        return sortedAccount;
    }

    private List<AccountResponseDto> sortAccounts(List<AccountResponseDto> accounts1, List<AccountResponseDto> accounts2) {
        accounts1.addAll(accounts2);

//        var grouped = accounts1.stream().collect(Collectors.groupingBy(account -> account.getTimestamp()));
        var sorted = accounts1.stream().sorted(account -> account.getTimestamp())
        return null;
    }
}
