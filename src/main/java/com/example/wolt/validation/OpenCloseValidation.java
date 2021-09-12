package com.example.wolt.validation;

import com.example.wolt.dto.InputTimeDto;

public interface OpenCloseValidation {
    boolean validateOpenCloseHour(InputTimeDto inputTimeDto);
}