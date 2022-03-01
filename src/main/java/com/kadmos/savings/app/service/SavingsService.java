package com.kadmos.savings.app.service;

import com.kadmos.savings.app.dal.SavingsMapper;
import com.kadmos.savings.app.exception.SavingsBaseException;
import com.kadmos.savings.app.model.*;
import com.kadmos.savings.app.util.ResponseBuilder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
@RequiredArgsConstructor
public class SavingsService {

    private static final Logger LOG = LoggerFactory.getLogger(SavingsService.class);

    private final @NonNull SavingsMapper savingsMapper;

    public RestResponse<BalanceResponse> getCurrentBalance(Long accountNo) {
        BalanceResponse currentBalance = savingsMapper.getCurrentBalance(accountNo);
        return new RestResponse<>(currentBalance, HttpStatus.OK);
    }

    public RestResponse<TransactionResponse> doTransaction(TransactionRequest request) {
        BigDecimal current;
        if (TransactionType.DEPOSIT.equals(request.getTransactionType())) {
            BalanceResponse currentBalance = savingsMapper.getCurrentBalance(request.getAccountNo());
            current = currentBalance.getBalance().add(request.getAmount());
            request.setBalance(current);
            savingsMapper.deposit(request);
            LOG.info("Deposited {} in the account: {}", request.getAmount(), request.getAccountNo());
        } else if (TransactionType.WITHDRAW.equals(request.getTransactionType())) {
            BalanceResponse currentBalance = savingsMapper.getCurrentBalance(request.getAccountNo());
            current = currentBalance.getBalance().subtract(request.getAmount());
            request.setBalance(current);
            savingsMapper.withdraw(request);
            LOG.info("Withdrawn {} from the account: {}", request.getAmount(), request.getAccountNo());
        } else {
            throw new SavingsBaseException("transaction type doesn't support!");
        }
        return new RestResponse<>(ResponseBuilder.buildDepositResponse(request, current), HttpStatus.OK);
    }

    public RestResponse<AccountCreateResponse> createAccount(AccountCreateRequest request) {
        savingsMapper.createAccount(request);
        LOG.info("Account created successfully for customer: {} {}", request.getFirstName(), request.getLastName());
        TransactionRequest txRequest = new TransactionRequest();
        txRequest.setAccountNo(request.getId());
        txRequest.setAccountType(request.getAccountType());
        txRequest.setAmount(request.getInitialDeposit());
        txRequest.setBalance(request.getInitialDeposit());
        txRequest.setTransactionType(TransactionType.DEPOSIT);
        txRequest.setDescription("initial deposit");
        savingsMapper.deposit(txRequest);
        LOG.info("Initial amount of {} deposited for customer: {} {}", request.getInitialDeposit(), request.getFirstName(), request.getLastName());
        return new RestResponse<>(ResponseBuilder.buildAccountCreation(request), HttpStatus.OK);
    }
}
