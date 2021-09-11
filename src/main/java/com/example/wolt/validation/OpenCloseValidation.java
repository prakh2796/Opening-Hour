package com.example.wolt.validation;

import com.example.wolt.dto.InputTimeDto;
import org.springframework.stereotype.Component;

public interface OpenCloseValidation {
    boolean validateOpenCloseHour(InputTimeDto inputTimeDto);
}