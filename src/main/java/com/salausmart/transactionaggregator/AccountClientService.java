package com.salausmart.transactionaggregator;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class AccountClientService {

    private final RestClient restClient;

    public AccountClientService(RestClient restClient) {
        this.restClient = restClient;
    }

    @Cacheable(cacheNames = "accounts", key = "#baseUrl + '_' + #account")
    public List<AccountResponseDto> getAccountResponse(String baseUrl, String account) {
        int maxRetries = 5;
        int attempt = 0;
        List<AccountResponseDto> accountTransactions = null;

        while (attempt < maxRetries) {
            try {
                accountTransactions = restClient.get()
                        .uri(baseUrl + "/transactions?account=" + account)
                        .retrieve()
                        .onStatus(httpStatusCode -> httpStatusCode.value() == 429, ((request, response) -> {
                            throw new RuntimeException(response.getStatusText()); //TODO Use custom exception
                        }))
                        .onStatus(httpStatusCode -> httpStatusCode.value() == 503, ((request, response) -> {
                            throw new RuntimeException(response.getStatusText()); //TODO Use custom exception
                        }))
                        .body(new ParameterizedTypeReference<>() {
                        });
            } catch (Exception ex) {
                attempt++;
                if (attempt == maxRetries)
                    return accountTransactions;
            }
        }
        return accountTransactions;
    }
}
