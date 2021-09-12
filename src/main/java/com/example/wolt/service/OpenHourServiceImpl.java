package com.example.wolt.service;

import com.example.wolt.dto.InputTimeDto;
import com.example.wolt.dto.response.ErrorResponse;
import com.example.wolt.dto.response.ServiceResponse;
import com.example.wolt.dto.response.SuccessResponse;
import com.example.wolt.dto.TimeDto;
import com.example.wolt.enums.Type;
import com.example.wolt.utils.Constants;
import com.example.wolt.utils.OpenHourUtil;
import com.example.wolt.validation.OpenHourValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OpenHourServiceImpl implements IOpenHourService {

    private static final Logger logger = LoggerFactory.getLogger(OpenHourServiceImpl.class);

    @Autowired
    OpenHourValidation openHourValidation;

    /**
     *
     * @param inputTimeDto Open Hours DTO
     * @return Service Response
     */
    @Override
    public ResponseEntity<ServiceResponse> getReadableOpenHours(InputTimeDto inputTimeDto) {
        logger.info("Validating input JSON");
        if(!openHourValidation.validateType(inputTimeDto)) {
            logger.info("Incorrect type provided");
            return new ResponseEntity<ServiceResponse>(new ErrorResponse(Constants.INVALID_JSON_STRING), HttpStatus.BAD_REQUEST);
        }
        if(!openHourValidation.validateValue(inputTimeDto)) {
            logger.info("Invalid value provided");
            return new ResponseEntity<ServiceResponse>(new ErrorResponse(Constants.INVALID_JSON_STRING), HttpStatus.BAD_REQUEST);
        }
        if(!openHourValidation.validateOpenCloseHour(inputTimeDto)) {
            logger.info("Invalid JSON: Incorrect open-close format");
            return new ResponseEntity<ServiceResponse>(new ErrorResponse(Constants.INVALID_JSON_STRING), HttpStatus.BAD_REQUEST);
        }
        logger.info("JSON Validation Successful");
        LinkedHashMap<String, String> response = new LinkedHashMap<>();
        try {
            response.put(Constants.MONDAY, processOpenHours(inputTimeDto.getMonday(), Constants.MONDAY, response));
            response.put(Constants.TUESDAY, processOpenHours(inputTimeDto.getTuesday(), Constants.TUESDAY, response));
            response.put(Constants.WEDNESDAY, processOpenHours(inputTimeDto.getWednesday(), Constants.WEDNESDAY, response));
            response.put(Constants.THURSDAY, processOpenHours(inputTimeDto.getThursday(), Constants.THURSDAY, response));
            response.put(Constants.FRIDAY, processOpenHours(inputTimeDto.getFriday(), Constants.FRIDAY, response));
            response.put(Constants.SATURDAY, processOpenHours(inputTimeDto.getSaturday(), Constants.SATURDAY, response));
            response.put(Constants.SUNDAY, processOpenHours(inputTimeDto.getSunday(), Constants.SUNDAY, response));
        } catch (Exception e) {
            logger.info("Error Processing JSON");
            return new ResponseEntity<ServiceResponse>(new ErrorResponse(Constants.ERROR_PROCESSING), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("JSON Processed successfully!");
        return new ResponseEntity<ServiceResponse>(new SuccessResponse(response), HttpStatus.OK);
    }

    /**
     *
     * @param openHoursList Time DTO for a particular day of the week
     * @param day day of the week
     * @param response Human readable open hour format for a particular day
     * @return
     */
    public String processOpenHours(List<TimeDto> openHoursList, String day, LinkedHashMap<String, String> response) {
        logger.info("Processing open hour for {}", day);
        StringBuilder builder = new StringBuilder();
        if (openHoursList.size() > 0) {
            for (int i = 0; i < openHoursList.size(); i++) {
                if (i == 0 && openHoursList.get(i).getType().equalsIgnoreCase(Type.CLOSE.name())) {
                    logger.info("Previous day opening found");
                    String previousDay = OpenHourUtil.lookupPreviousDay(day);
                    String value = response.get(previousDay);
                    value = value.concat(" - " + OpenHourUtil.convertUnixTimeToDate(openHoursList.get(i).getValue()));

                    response.replace(previousDay, value);
                } else {
                    if (openHoursList.get(i).getType().equalsIgnoreCase(Type.OPEN.name())) {
                        builder.append(OpenHourUtil.convertUnixTimeToDate(openHoursList.get(i).getValue()));
                    } else if (openHoursList.get(i).getType().equalsIgnoreCase(Type.CLOSE.name())) {
                        builder.append(" - " + OpenHourUtil.convertUnixTimeToDate(openHoursList.get(i).getValue()) + ", ");
                    }
                }
            }
        } else {
            logger.info("Restaurant closed!");
            builder.append("Closed");
        }
        if(builder.charAt(builder.length()-2) == ',')
            builder.deleteCharAt(builder.length()-2);
        return builder.toString().trim();
    }
}
