package com.salausmart.transactionaggregator;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class AggregateService {

    private final RestClient restClient;

    public AggregateService(RestClient restClient) {
        this.restClient = restClient;
    }

    public String aggregate() {

        return restClient.get()
                .uri("http://localhost:8889/ping")
                .retrieve()
                .body(String.class);
    }
}
