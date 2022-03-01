package com.kadmos.savings.app.validation;

import com.kadmos.savings.app.dal.SavingsMapper;
import com.kadmos.savings.app.exception.SavingsBaseException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidateRequest {

    private final @NonNull SavingsMapper savingsMapper;

    public void validateAccountNo(Long accountNo) {
        if (savingsMapper.checkAccountExists(accountNo) <= 0) {
            throw new SavingsBaseException("Account Number doesn't exists! Please check the account number");
        }
    }

}
