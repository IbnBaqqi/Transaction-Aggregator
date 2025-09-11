package com.salausmart.transactionaggregator;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class AccountResponseDto implements Comparable<AccountResponseDto>{
    private String id;
    private String serverId;
    private String account;
    private String amount;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    private LocalDateTime timestamp;

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
