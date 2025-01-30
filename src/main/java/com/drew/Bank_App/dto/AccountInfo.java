package com.drew.Bank_App.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountInfo {

    @Schema(
            name = "User's account name."
    )
    private String accountName;
    
    @Schema(
            name = "User's auto generated account number."
    )
    private String accountNumber;

    @Schema(
            name = "User's current account balance."
    )
    private BigDecimal accountBalance;
}
