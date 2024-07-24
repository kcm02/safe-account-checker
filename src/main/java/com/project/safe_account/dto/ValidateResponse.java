package com.project.safe_account.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ValidateResponse {
    private boolean valid;
    private String message;
}
