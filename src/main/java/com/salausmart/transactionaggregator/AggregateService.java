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

    public String aggregate(String account) {

        List<AccountResponseDto> accounts = restClient.get()
                .uri("http://localhost:8889/transactions?account=" + account)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
        return null;
    }
}
