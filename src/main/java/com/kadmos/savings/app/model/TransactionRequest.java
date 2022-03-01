package com.kadmos.savings.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {

    private Long accountNo;
    private String accountType;
    private String description;
    private BigDecimal amount;
    private BigDecimal balance;
    private TransactionType transactionType;
}
