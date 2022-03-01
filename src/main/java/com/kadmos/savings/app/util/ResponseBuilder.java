package com.kadmos.savings.app.util;

import com.kadmos.savings.app.model.AccountCreateRequest;
import com.kadmos.savings.app.model.AccountCreateResponse;
import com.kadmos.savings.app.model.TransactionRequest;
import com.kadmos.savings.app.model.TransactionResponse;

import java.math.BigDecimal;

public class ResponseBuilder {

    public static TransactionResponse buildDepositResponse(TransactionRequest request, BigDecimal currentBalance) {
        TransactionResponse response = new TransactionResponse();
        response.setAccountNo(request.getAccountNo());
        response.setAccountType(request.getAccountType());
        response.setCurrentBalance(currentBalance);
        response.setTransactionType(request.getTransactionType().name());
        return response;
    }

    public static AccountCreateResponse buildAccountCreation(AccountCreateRequest request) {
        AccountCreateResponse response = new AccountCreateResponse();
        response.setAccountNo(request.getId());
        response.setAccountType(request.getAccountType());
        response.setBalance(request.getInitialDeposit());
        return response;
    }
}
