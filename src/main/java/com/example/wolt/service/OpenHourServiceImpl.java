package com.example.wolt.service;

import com.example.wolt.dto.InputTimeDto;
import com.example.wolt.dto.response.ErrorResponse;
import com.example.wolt.dto.response.ServiceResponse;
import com.example.wolt.dto.response.SuccessResponse;
import com.example.wolt.dto.TimeDto;
import com.example.wolt.enums.Day;
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
            return new ResponseEntity<ServiceResponse>(new ErrorResponse(Constants.INVALID_JSON_STRING + ". " + Constants.INVALID_TYPE), HttpStatus.BAD_REQUEST);
        }
        logger.info("Type validation successful");
        if(!openHourValidation.validateValue(inputTimeDto)) {
            logger.info("Invalid value provided");
            return new ResponseEntity<ServiceResponse>(new ErrorResponse(Constants.INVALID_JSON_STRING + ". " + Constants.INVALID_VALUE), HttpStatus.BAD_REQUEST);
        }
        logger.info("Value validation successful");
        if(!openHourValidation.validateOpenCloseHour(inputTimeDto)) {
            logger.info("Invalid JSON: Incorrect open-close format");
            return new ResponseEntity<ServiceResponse>(new ErrorResponse(Constants.INVALID_JSON_STRING + ". " + Constants.INVALID_OPEN_CLOSE_HOUR_FORMAT), HttpStatus.BAD_REQUEST);
        }
        logger.info("Open CLose Hour validation successful");
        logger.info("JSON Validation Successful");
        LinkedHashMap<String, String> response = new LinkedHashMap<>();
        try {
            response.put(Day.MONDAY.name(), processOpenHours(inputTimeDto.getMonday(), Day.MONDAY.getDayOfWeek(), response));
            response.put(Day.TUESDAY.name(), processOpenHours(inputTimeDto.getTuesday(), Day.TUESDAY.getDayOfWeek(), response));
            response.put(Day.WEDNESDAY.name(), processOpenHours(inputTimeDto.getWednesday(), Day.WEDNESDAY.getDayOfWeek(), response));
            response.put(Day.THURSDAY.name(), processOpenHours(inputTimeDto.getThursday(), Day.THURSDAY.getDayOfWeek(), response));
            response.put(Day.FRIDAY.name(), processOpenHours(inputTimeDto.getFriday(), Day.FRIDAY.getDayOfWeek(), response));
            response.put(Day.SATURDAY.name(), processOpenHours(inputTimeDto.getSaturday(), Day.SATURDAY.getDayOfWeek(), response));
            response.put(Day.SUNDAY.name(), processOpenHours(inputTimeDto.getSunday(), Day.SUNDAY.getDayOfWeek(), response));
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
     * @param dayOfWeek day of the week
     * @param response Human readable open hour format for a particular day
     * @return
     */
    public String processOpenHours(List<TimeDto> openHoursList, int dayOfWeek, LinkedHashMap<String, String> response) {
        logger.info("Processing open hour for {}", Day.valueOf(dayOfWeek));
        StringBuilder builder = new StringBuilder();
        if (openHoursList.size() > 0) {
            for (int i = 0; i < openHoursList.size(); i++) {
                if (i == 0 && openHoursList.get(i).getType().equalsIgnoreCase(Type.CLOSE.name())) {
                    logger.info("Previous day opening found");
                    int previousDay = OpenHourUtil.lookupPreviousDay(dayOfWeek);
                    String value = response.get(Day.valueOf(previousDay).name());
                    value = value.concat(" - " + OpenHourUtil.convertUnixTimeToDate(openHoursList.get(i).getValue()));

                    response.replace(Day.valueOf(previousDay).name(), value);
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
