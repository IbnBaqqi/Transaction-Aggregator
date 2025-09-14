package com.salausmart.transactionaggregator;

import org.springframework.stereotype.Service;


import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@Service
public class AggregateService {


    private final AccountClientService accountClientService;

    public AggregateService(AccountClientService accountClientService) {
        this.accountClientService = accountClientService;
    }

    private final String accountUrl1 = "http://localhost:8888";
    private final String accountUrl2 = "http://localhost:8889";

    public List<AccountResponseDto> aggregate(String account) {

        CompletableFuture<List<AccountResponseDto>> futureAccount1 =
                CompletableFuture.supplyAsync( () -> accountClientService.getAccountResponse(accountUrl1, account));

        CompletableFuture<List<AccountResponseDto>> futureAccount2 =
                CompletableFuture.supplyAsync(() -> accountClientService.getAccountResponse(accountUrl2, account));

//        var acc1 = futureAccount1.join();
//        var acc2 = futureAccount2.join();
//        return sortAccounts(acc1, acc2); //this didn't work because .join() also blocks

        return futureAccount1.thenCombine(futureAccount2, this::sortAccounts).join();
    }

    public List<AccountResponseDto> sortAccounts(List<AccountResponseDto> account1, List<AccountResponseDto> account2) {

        return Stream.concat(account1.stream(), account2.stream())
                .sorted(Comparator.comparing(AccountResponseDto::getTimestamp).reversed())
                .toList();
    }
}
