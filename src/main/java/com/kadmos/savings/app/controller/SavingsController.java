package com.kadmos.savings.app.controller;

import com.kadmos.savings.app.model.*;
import com.kadmos.savings.app.service.SavingsService;
import com.kadmos.savings.app.validation.ValidateRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/savings/b")
@RequiredArgsConstructor
public class SavingsController {

    private final @NonNull SavingsService savingsService;
    private final @NonNull ValidateRequest validateRequest;

    @PostMapping("/create")
    public RestResponse<AccountCreateResponse> createAccount(@RequestBody AccountCreateRequest request) {
        return savingsService.createAccount(request);
    }

    @PostMapping("/balance")
    public RestResponse<TransactionResponse> depositAmount(@RequestBody TransactionRequest request) {
        validateRequest.validateAccountNo(request.getAccountNo());
        return savingsService.doTransaction(request);
    }

    @GetMapping("/balance")
    public RestResponse<BalanceResponse> getCurrentBalance(@RequestParam("accountNo") Long accountNo) {
        validateRequest.validateAccountNo(accountNo);
        return savingsService.getCurrentBalance(accountNo);
    }
}
