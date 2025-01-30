package com.drew.Bank_App.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String transactionId;

    private String transactionType;

    //TODO
    private String transactionDate;

    private String accountNumber;

    private BigDecimal amount;

    private String status;

}
