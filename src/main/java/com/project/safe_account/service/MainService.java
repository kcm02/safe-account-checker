package com.project.safe_account.service;

import com.project.safe_account.dto.ValidateResponse;
import com.project.safe_account.util.AccountNumberValidatorUtil;
import org.springframework.stereotype.Service;

@Service
public class MainService {

    public ValidateResponse isValidAccount(String bankName, String accountNumber) {
        return AccountNumberValidatorUtil.validateAccountNumber(bankName, accountNumber);
    }
}
