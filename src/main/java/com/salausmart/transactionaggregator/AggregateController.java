package com.salausmart.transactionaggregator;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aggregate")
public class AggregateController {

    private final AggregateService aggregateService;

    public AggregateController(AggregateService aggregateService) {
        this.aggregateService = aggregateService;
    }

    @GetMapping()
    public ResponseEntity<String> aggregate(@RequestParam(name = "account") String account) {
        var response = aggregateService.aggregate(account);
        return ResponseEntity.ok(response);
    }
}