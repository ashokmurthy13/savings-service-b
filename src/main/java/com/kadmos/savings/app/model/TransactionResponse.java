package com.kadmos.savings.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {

    private Long accountNo;
    private BigDecimal currentBalance;
    private String accountType;
    private String transactionType;

}
