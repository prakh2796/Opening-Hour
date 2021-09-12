package com.example.wolt.controller;

import com.example.wolt.dto.InputTimeDto;
import com.example.wolt.dto.response.ServiceResponse;
import com.example.wolt.service.IOpenHourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/open-hour")
public class OpenHourController {

    @Autowired
    IOpenHourService openHourService;

    /**
     *
     * @param inputTimeDto Open Hours DTO
     * @return Service Response
     */
    @PostMapping("/convert")
    public ResponseEntity<ServiceResponse> hotelTiming(@Valid @RequestBody InputTimeDto inputTimeDto) {
        return openHourService.getReadableOpenHours(inputTimeDto);
    }
}
