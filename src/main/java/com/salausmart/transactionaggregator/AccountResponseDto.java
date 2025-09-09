package com.salausmart.transactionaggregator;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccountResponseDto {
    private String id;
    private String serverId;
    private String account;
    private LocalDateTime timestamp;
}
