package com.drew.Bank_App.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDto {
    private String transactionType;

    private String transactionDate;

    private String accountNumber;

    private BigDecimal amount;

    private String status;
}
