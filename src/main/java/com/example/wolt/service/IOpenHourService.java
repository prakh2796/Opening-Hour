package com.example.wolt.service;

import com.example.wolt.dto.InputTimeDto;
import com.example.wolt.dto.response.ServiceResponse;
import org.springframework.http.ResponseEntity;

public interface IOpenHourService {
    public ResponseEntity<ServiceResponse> getReadableOpenHours(InputTimeDto inputTimeDto);
}
