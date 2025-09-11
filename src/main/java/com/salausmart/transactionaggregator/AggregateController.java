package com.salausmart.transactionaggregator;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/aggregate")
public class AggregateController {

    private final AggregateService aggregateService;

    public AggregateController(AggregateService aggregateService) {
        this.aggregateService = aggregateService;
    }

    @GetMapping()
    public List<AccountResponseDto> aggregate(@RequestParam(name = "account") String account) {
        var response = aggregateService.aggregate(account);
        return response;
    }
}