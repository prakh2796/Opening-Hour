package com.example.wolt.controller;

import com.example.wolt.dto.InputTimeDto;
import com.example.wolt.dto.response.ServiceResponse;
import com.example.wolt.service.IOpenHourService;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "Get the JSON format of the opening hours of the restaurant" +
            "and returns it human readable format")

    @PostMapping("/convert")
    public ResponseEntity<ServiceResponse> hotelTiming(@Valid @RequestBody InputTimeDto inputTimeDto) {
        return openHourService.getReadableOpenHours(inputTimeDto);
    }
}
