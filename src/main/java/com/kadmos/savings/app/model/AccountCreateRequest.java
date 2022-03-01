package com.kadmos.savings.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountCreateRequest {

    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String accountType;
    private BigDecimal initialDeposit;
}
