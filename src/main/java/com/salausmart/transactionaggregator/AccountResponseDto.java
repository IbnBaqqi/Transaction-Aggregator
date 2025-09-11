package com.salausmart.transactionaggregator;

import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
public class AccountResponseDto implements Comparable<AccountResponseDto>{
    private String id;
    private String serverId;
    private String account;
    private String amount;
    private OffsetDateTime timestamp;

    @Override
    public int compareTo(AccountResponseDto accountResponseDto) {
        if (this.timestamp.isBefore(accountResponseDto.getTimestamp()))
            return 1;
        else if (this.timestamp.isAfter(accountResponseDto.getTimestamp()))
            return -1;
        else
            return 0;
    }
}
