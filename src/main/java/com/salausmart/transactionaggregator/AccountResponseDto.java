package com.salausmart.transactionaggregator;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class AccountResponseDto {
    private String id;
    private String serverId;
    private String account;
    private String amount;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    private LocalDateTime timestamp;

}
