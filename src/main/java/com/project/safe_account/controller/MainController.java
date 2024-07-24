package com.project.safe_account.controller;

import com.project.safe_account.dto.ValidateResponse;
import com.project.safe_account.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    @PostMapping("/validate")
    public ValidateResponse validateAccount(@RequestParam String bankName, @RequestParam String accountNumber) {
        return mainService.isValidAccount(bankName, accountNumber);
    }
}
